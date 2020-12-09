package com.example.amazighquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;


public class CategoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CategoryAdapter adapter;
    DatabaseReference mbase;
    ArrayList<CategoryModel> listmodel;
    LinearLayoutManager linearLayoutManager;

    private static final String TAG = "CategoryActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        mbase = FirebaseDatabase.getInstance().getReference();
        recyclerView = findViewById(R.id.recycler1);
        listmodel = new ArrayList<>();


        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        getMethode();
        getData();

    }

    public String getMethode(){
        if(getIntent().hasExtra("methode")){
            return getIntent().getStringExtra("methode");
        }

        return "geen methode";
    }

    public void getData(){

            Query query = mbase.child("Categories");

            query.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            CategoryModel categoryModel = new CategoryModel();

                            categoryModel.setCategorieën(snapshot.getKey());

                            listmodel.add(categoryModel);
                        }

                        adapter = new CategoryAdapter(getApplicationContext(), listmodel, getMethode());
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

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