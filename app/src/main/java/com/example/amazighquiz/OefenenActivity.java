package com.example.amazighquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class OefenenActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    ViewPager2 viewPager2;
    List<OefenenModel> list;
    String category;
    OefenenAdapter adapter;

    TextView titlecategory;

    private static final String TAG = "OefenenActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oefenen);

        titlecategory = findViewById(R.id.category_title);

        viewPager2 = findViewById(R.id.viewpager);
        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        list = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        getCategory();
        getData();
    }

    public void getCategory(){

        if(getIntent().hasExtra("category")){
            category = getIntent().getStringExtra("category");
            titlecategory.setText(category);
        }
    }

    public void getData(){

        Query query = databaseReference.child("Categories").child(category);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        OefenenModel oefenenModel = new OefenenModel();

                        oefenenModel.setFoto(snapshot.child("Foto").getValue(String.class));
                        oefenenModel.setGeluid(snapshot.child("Geluid").getValue(String.class));
                        oefenenModel.setWordAM(snapshot.child("WordAM").getValue(String.class));
                        oefenenModel.setWordNL(snapshot.getKey());

                        list.add(oefenenModel);
                    }

                    adapter = new OefenenAdapter(getApplicationContext(), list);
                    viewPager2.setAdapter(adapter);
                }

                else {
                    Log.d(TAG, "elseDataChange: no data");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "loadPost:onCancelled", error.toException());

            }
        });
    }
}