package com.example.amazighquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        mbase = FirebaseDatabase.getInstance().getReference();
        recyclerView = findViewById(R.id.recycler1);
        listmodel = new ArrayList<>();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        clearData();getData();
    }

    public void getData(){

            Query query = mbase.child("Categories");

            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChildren()){

                        clearData();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            CategoryModel categoryModel = new CategoryModel();

                            categoryModel.setCategorieën(snapshot.getKey());

                            listmodel.add(categoryModel);

                        }

                        adapter = new CategoryAdapter(getApplicationContext(),listmodel);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        public void clearData(){

            if (listmodel != null) {
                listmodel.clear();
                if (adapter != null){
                    adapter.notifyDataSetChanged();
                }
            }
            listmodel = new ArrayList<>();
        }
        }