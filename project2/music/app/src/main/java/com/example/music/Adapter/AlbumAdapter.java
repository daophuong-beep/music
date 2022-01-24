package com.example.music.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.music.Activity.DanhSachBaiHatActivity;
import com.example.music.Model.Album;
import com.example.music.R;
import com.example.music.Service.DiaChiIP;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder>{
    Context context;
    ArrayList<Album> mangAlbum;

    public AlbumAdapter(Context context, ArrayList<Album> mangAlbum) {
        this.context = context;
        this.mangAlbum = mangAlbum;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.dong_album,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album= mangAlbum.get(position);
        holder.tvcasialbum.setText(album.getTenCaSiAlbum());
        holder.tvtenalbum.setText(album.getTenAlbum());
        Picasso.with(context).load(DiaChiIP.ip+album.getHinhAlbum()).into(holder.ivalbum);
    }

    @Override
    public int getItemCount() {
        return mangAlbum.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivalbum;
        TextView tvtenalbum;
        TextView tvcasialbum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivalbum=itemView.findViewById(R.id.ivalbum);
            tvtenalbum=itemView.findViewById(R.id.tvtenalbum);
            tvcasialbum=itemView.findViewById(R.id.tvtencasialbum);

            ivalbum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, DanhSachBaiHatActivity.class);
                    intent.putExtra("itemalbum",mangAlbum.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
