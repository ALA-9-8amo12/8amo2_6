package com.example.amazighquiz;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.List;


public class OefenenAdapter extends RecyclerView.Adapter<OefenenAdapter.OefenenViewholder> {

    Context context;
    List<OefenenModel> oefenenModel;

    public OefenenAdapter(Context context, List<OefenenModel> oefenenmodel) {

        this.context = context;
        this.oefenenModel = oefenenmodel;
    }

    @NonNull
    @Override
    public OefenenViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cradview_oefenen, parent, false);
        return new OefenenViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OefenenAdapter.OefenenViewholder holder, final int position) {

        Glide.with(context).load(oefenenModel.get(position).getFoto()).into(holder.Foto);
        holder.Geluid.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                try {
                    geluid(oefenenModel.get(position).getGeluid());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        holder.WordAM.setText(oefenenModel.get(position).getWordAM());
        holder.WordNL.setText(oefenenModel.get(position).getWordNL());
    }

    @Override
    public int getItemCount() {

        return oefenenModel.size();
    }


    class OefenenViewholder extends RecyclerView.ViewHolder {

        Button Geluid;
        ImageView Foto;
        TextView WordAM;
        TextView WordNL;

        public OefenenViewholder(@NonNull View itemView) {
            super(itemView);

            WordNL = itemView.findViewById(R.id.WordNL);
            Foto = itemView.findViewById(R.id.Foto);
            Geluid = itemView.findViewById(R.id.Geluid);
            WordAM = itemView.findViewById(R.id.WordAM);

        }
    }

    public void geluid (String path) throws IOException {

        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );
        mediaPlayer.setDataSource(path);
        mediaPlayer.prepare();
        mediaPlayer.start();
    }
}
