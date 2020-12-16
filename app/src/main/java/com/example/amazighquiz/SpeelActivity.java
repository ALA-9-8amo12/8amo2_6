package com.example.amazighquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SpeelActivity extends AppCompatActivity implements SpeelAdapter.Clicklistener{

    RecyclerView recyclerView;
    Button button;

    DatabaseReference databaseReference;
    List<SpeelModel> speelModelList;
    List<SpeelModel> pagelist;

    MediaPlayer mediaPlayer;
    Handler handler;

    float totaal = 0;
    float waarde;
    int score;

    int counter = 0;
    int wrong = 0;

    private SpeelAdapter.Clicklistener clicklistener;

    private static final String TAG = "SpeelActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speel);

        recyclerView = findViewById(R.id.recycler);
        button = findViewById(R.id.beluister);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        speelModelList = new ArrayList<>();

        handler = new Handler();

        getData();
        buttonbeluister();
    }

    public String getCategory(){
        if(getIntent().hasExtra("category")){
            return getIntent().getStringExtra("category");
        }

        return "geen category";
    }

    public void getData(){

        Query query = databaseReference.child("Categories").child(getCategory());

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        SpeelModel speelModel = new SpeelModel();

                        speelModel.setImage(snapshot.child("Foto").getValue(String.class));
                        speelModel.setSound(snapshot.child("Geluid").getValue(String.class));

                        speelModelList.add(speelModel);
                    }


                    waarde = 10 / (float) speelModelList.size();

                    Log.d(TAG, "onDataChange: " + speelModelList.get(counter).getSound());

                    Collections.shuffle(speelModelList);
                    createsound(speelModelList.get(counter).getSound());
                    makeAdapter();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void setAdapter(){
        SpeelAdapter adapter = new SpeelAdapter(getApplicationContext(), pagelist, this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(SpeelActivity.this, 2));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void createsound(String path){
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );

        try {
            mediaPlayer.setDataSource(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void makeAdapter(){
        pagelist = new ArrayList<>();
        Random r = new Random();
        int rgetal;

        ArrayList<Integer> pastnumbers = new ArrayList<Integer>();
        pagelist.add(speelModelList.get(counter));
        pastnumbers.add(counter);

        boolean flag = false;

        for(int i = 0; i < 5; i++){

            rgetal = r.nextInt(speelModelList.size());

            for(int x = 0; x < pastnumbers.size(); x++){
                if(rgetal == pastnumbers.get(x)){
                    flag = true;
                }
            }

            if(!flag){
                pagelist.add(speelModelList.get(rgetal));
                pastnumbers.add(rgetal);
            }

            else{
                i--;
            }

            flag = false;
        }

        Collections.shuffle(pagelist);
        setAdapter();

        mediaPlayer.start();

    }

    public void onItemClick(int position){
        RecyclerView.ViewHolder view = recyclerView.findViewHolderForAdapterPosition(position);
        final RecyclerView.ViewHolder good = recyclerView.findViewHolderForAdapterPosition(pagelist.indexOf(speelModelList.get(counter)));

        if(pagelist.indexOf(speelModelList.get(counter)) == position){
            wrong = 0;
            counter++;

            view.itemView.findViewById(R.id.imagespeel).setBackgroundColor(Color.GREEN);

            handler.postDelayed(new Runnable() {
                public void run() {
                    createsound(speelModelList.get(counter).getSound());
                    makeAdapter();
                }
            }, 100);
        }

        else{
            wrong++;

            view.itemView.findViewById(R.id.imagespeel).setBackgroundColor(Color.RED);

            if(wrong == 3){
                wrong = 0;
                counter++;

                handler.postDelayed(new Runnable() {
                    public void run() {
                        good.itemView.findViewById(R.id.imagespeel).setBackgroundColor(Color.GREEN);
                    }
                }, 200);



                handler.postDelayed(new Runnable() {
                    public void run() {
                        createsound(speelModelList.get(counter).getSound());
                        makeAdapter();
                    }
                }, 500);
            }
        }
    }

    public void buttonbeluister(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
            }
        });
    }
}