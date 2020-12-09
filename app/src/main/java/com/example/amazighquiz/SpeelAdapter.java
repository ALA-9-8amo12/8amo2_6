package com.example.amazighquiz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class SpeelAdapter extends RecyclerView.Adapter<SpeelAdapter.SpeelViewHolder> {

    Context context;
    List<SpeelModel> speelModel;
    private Clicklistener listener;

    public SpeelAdapter(Context context, List<SpeelModel> speelModel, Clicklistener listener) {
        this.context = context;
        this.speelModel = speelModel;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SpeelAdapter.SpeelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_speel, parent, false);

        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.height = parent.getMeasuredHeight() / 4;
        view.setLayoutParams(lp);

        return new SpeelViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull SpeelAdapter.SpeelViewHolder holder, int position) {
        Glide.with(context).load(speelModel.get(position).getImage()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return speelModel.size();
    }

    public class SpeelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageView;
        Clicklistener clicklistener;

        public SpeelViewHolder(@NonNull View itemView, Clicklistener clicklistener) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imagespeel);
            this.clicklistener = clicklistener;

            itemView.setOnClickListener(this);
        }

        public void onClick(View v){
            clicklistener.onItemClick(getAdapterPosition());
        }
    }

    public interface Clicklistener{
        void onItemClick(int position);
    }
}
