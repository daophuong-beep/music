package com.example.music.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.music.Model.BaiHat;
import com.example.music.R;

import java.util.ArrayList;

public class PlayNhacAdapter extends RecyclerView.Adapter<PlayNhacAdapter.ViewHolder> {
    private Context context;
    private ArrayList<BaiHat> arrayListBaiHat;

    public PlayNhacAdapter(Context context, ArrayList<BaiHat> arrayListBaiHat) {
        this.context = context;
        this.arrayListBaiHat = arrayListBaiHat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dong_playbaihat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvCaSi.setText(arrayListBaiHat.get(position).getCaSi());
        holder.tvTenBaiHat.setText(arrayListBaiHat.get(position).getTenBaiHat());
        holder.tvSoThuTu.setText(String.valueOf(position+1));
    }

    @Override
    public int getItemCount() {
        return arrayListBaiHat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvSoThuTu;
        private TextView tvTenBaiHat;
        private TextView tvCaSi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSoThuTu=itemView.findViewById(R.id.tvsothutu);
            tvTenBaiHat=itemView.findViewById(R.id.tvtenbaihat);
            tvCaSi=itemView.findViewById(R.id.tvtencasi);
        }
    }
}
