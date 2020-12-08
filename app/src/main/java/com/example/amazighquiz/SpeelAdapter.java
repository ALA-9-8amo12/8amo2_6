package com.example.amazighquiz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SpeelAdapter extends RecyclerView.Adapter<SpeelAdapter.SpeelViewHolder> {

    Context context;
    List<SpeelModel> speelModel;

    public SpeelAdapter(Context context, List<SpeelModel> speelModel) {
        this.context = context;
        this.speelModel = speelModel;
    }

    @NonNull
    @Override
    public SpeelAdapter.SpeelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(0, parent, false);
        return new SpeelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpeelAdapter.SpeelViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return speelModel.size();
    }

    public class SpeelViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public SpeelViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(0);
        }
    }
}
