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

public class DanhSachAlbumAdapter extends RecyclerView.Adapter<DanhSachAlbumAdapter.ViewHolder>  {
    private Context context;
    private ArrayList<Album> albumArrayList;

    public DanhSachAlbumAdapter(Context context, ArrayList<Album> albumArrayList) {
        this.context = context;
        this.albumArrayList = albumArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.ovuong_album,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.with(context).load(DiaChiIP.ip+albumArrayList.get(position).getHinhAlbum()).into(holder.ivAlbum);
        Picasso.with(context).load(DiaChiIP.ip+albumArrayList.get(position).getHinhAlbum()).into(holder.ivIconAlbum);
        holder.tvTenAlbum.setText(albumArrayList.get(position).getTenAlbum());
    }

    @Override
    public int getItemCount() {
        return albumArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
    private ImageView ivAlbum;
    private ImageView ivIconAlbum;
    private TextView tvTenAlbum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAlbum=itemView.findViewById(R.id.ivalbum);
            ivIconAlbum=itemView.findViewById(R.id.iviconalbum);
            tvTenAlbum=itemView.findViewById(R.id.tvtenalbum);
            ivAlbum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, DanhSachBaiHatActivity.class);
                    intent.putExtra("itemalbum",albumArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
