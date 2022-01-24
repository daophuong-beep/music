package com.example.music.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.music.Activity.DanhSachBaiHatActivity;
import com.example.music.Model.Theloai;
import com.example.music.R;
import com.example.music.Service.DiaChiIP;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TheLoaiAdapter extends RecyclerView.Adapter <TheLoaiAdapter.ViewHolder>{

    private Context context;
    private ArrayList<Theloai> arrayListTheLoai;

    public TheLoaiAdapter(Context context, ArrayList<Theloai> arrayListTheLoai) {
        this.context = context;
        this.arrayListTheLoai = arrayListTheLoai;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.ovuong_theloai,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Theloai theloai=arrayListTheLoai.get(position);
        Picasso.with(context).load(DiaChiIP.ip+theloai.getHinhTheLoai()).into(holder.ivTheLoai);
        Picasso.with(context).load(DiaChiIP.ip+theloai.getHinhTheLoai()).into(holder.ivIconTheLoai);
        holder.tvTenTheLoai.setText(theloai.getTenTheLoai());
    }

    @Override
    public int getItemCount() {
        return arrayListTheLoai.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivTheLoai;
        private ImageView ivIconTheLoai;
        private TextView tvTenTheLoai;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivTheLoai=itemView.findViewById(R.id.ivalbum);
            ivIconTheLoai=itemView.findViewById(R.id.iviconalbum);
            tvTenTheLoai=itemView.findViewById(R.id.tvtenalbum);
            ivTheLoai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  Intent intent= new Intent(context, DanhSachBaiHatActivity.class);
                    intent.putExtra("itemtheloai",arrayListTheLoai.get(getPosition()));
                    context.startActivity(intent);
                    Toast.makeText(context,"đã nhận",Toast.LENGTH_LONG).show();
                }
            });
        }
    }

}
