package com.example.amazighquiz;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;



public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewholder> {

    Context context;
    ArrayList<CategoryModel> categoryModel;
    String methode;

    public CategoryAdapter(Context context, ArrayList<CategoryModel> categorymodel, String methode) {
        this.context = context;
        this.categoryModel = categorymodel;
        this.methode = methode;
    }


    @NonNull
    @Override
    public CategoryViewholder
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_main, parent, false);
        return new CategoryViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewholder holder, final int position) {

        holder.categories.setText(categoryModel.get(position).getCategorieën());
        holder.categories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;

                if (methode.equals("oefen")){
                    intent = new Intent(context, OefenenActivity.class);
                }

                else {
                    intent = new Intent(context, SpeelActivity.class);
                }

                intent.putExtra("category", categoryModel.get(position).getCategorieën());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryModel.size();
    }

    class CategoryViewholder extends RecyclerView.ViewHolder {

        Button categories;

        public CategoryViewholder(@NonNull View itemView) {
            super(itemView);

            categories = itemView.findViewById(R.id.Categorieën);
        }
    }

}
