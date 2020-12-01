package com.example.amazighquiz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;



public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewholder> {

    Context context;
    ArrayList<CategoryModel> categoryModel;

    public CategoryAdapter(Context context, ArrayList<CategoryModel> categorymodel) {
        this.context = context;
        this.categoryModel = categorymodel;
    }


    @NonNull
    @Override
    public CategoryViewholder
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_main, parent, false);
        return new CategoryViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewholder holder, int position) {

        holder.Categorieën.setText(categoryModel.get(position).getCategorieën());
    }

    @Override
    public int getItemCount() {
        return categoryModel.size();
    }

    class CategoryViewholder extends RecyclerView.ViewHolder {

        Button Categorieën;

        public CategoryViewholder(@NonNull View itemView) {
            super(itemView);

            Categorieën = itemView.findViewById(R.id.Categorieën);
        }
    }

}
