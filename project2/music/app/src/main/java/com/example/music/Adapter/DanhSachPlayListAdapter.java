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
import com.example.music.Model.Playlist;
import com.example.music.R;
import com.example.music.Service.DiaChiIP;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhSachPlayListAdapter extends RecyclerView.Adapter<DanhSachPlayListAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Playlist> danhSachPlayList;

    public DanhSachPlayListAdapter(Context context, ArrayList<Playlist> danhSachPlayList) {
        this.context = context;
        this.danhSachPlayList = danhSachPlayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.ovuong_playlist,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Playlist playlist=danhSachPlayList.get(position);
        Picasso.with(context).load(DiaChiIP.ip+playlist.getHinhPlayList()).into(holder.ivPlayList);
        Picasso.with(context).load(DiaChiIP.ip+playlist.getIcon()).into(holder.ivIconPlayList);
        holder.tvTenPlayList.setText(playlist.getTen());
    }

    @Override
    public int getItemCount() {
        return danhSachPlayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
    private ImageView ivPlayList;
    private ImageView ivIconPlayList;
    private TextView tvTenPlayList;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPlayList=itemView.findViewById(R.id.ivalbum);
            ivIconPlayList=itemView.findViewById(R.id.iviconalbum);
            tvTenPlayList=itemView.findViewById(R.id.tvtenalbum);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(context, DanhSachBaiHatActivity.class);
                    intent.putExtra("itemplaylist",danhSachPlayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
