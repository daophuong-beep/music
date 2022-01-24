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

import com.example.music.Activity.PlayNhacActivity;
import com.example.music.Model.BaiHat;
import com.example.music.R;
import com.example.music.Service.APIService;
import com.example.music.Service.Dataservice;
import com.example.music.Service.DiaChiIP;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaiHatHotAdapter extends RecyclerView.Adapter<BaiHatHotAdapter.Viewholder> {
    Context context;
    ArrayList<BaiHat> arrayListBaiHat;

    public BaiHatHotAdapter(Context context, ArrayList<BaiHat> arrayListBaiHat) {
        this.context = context;
        this.arrayListBaiHat = arrayListBaiHat;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dong_baihat_yeuthich,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        BaiHat baiHat=arrayListBaiHat.get(position);
        holder.tvTenBaiHat.setText(baiHat.getTenBaiHat());
        holder.tvTenCaSi.setText(baiHat.getCaSi());
        Picasso.with(context).load(DiaChiIP.ip+baiHat.getHinhBaiHat()).into(holder.ivBaiHat);
    }

    @Override
    public int getItemCount() {
        return arrayListBaiHat.size();
    }

    class Viewholder extends RecyclerView.ViewHolder{
        TextView tvTenBaiHat,tvTenCaSi;
        ImageView ivBaiHat,iviconlove;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tvTenBaiHat=itemView.findViewById(R.id.tvtenbaihat);
            tvTenCaSi=itemView.findViewById(R.id.tvtencasi);
            ivBaiHat=itemView.findViewById(R.id.ivbaihat);
            iviconlove=itemView.findViewById(R.id.iviconlove);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc",arrayListBaiHat.get(getPosition()));
                    context.startActivity(intent);
                }
            });

            iviconlove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(context, tvTenBaiHat.getText().toString(),Toast.LENGTH_SHORT).show();

                    Dataservice dataservice= APIService.getService();
                    Call<String> callBack=dataservice.updateLuotThich("1",arrayListBaiHat.get(getPosition()).getIdBaiHat());
                    callBack.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua= response.body();
                            if(ketqua.equals("success")){
                                iviconlove.setImageResource(R.drawable.iconloved);
                                Toast.makeText(context, "đã thích",Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context, "lỗi",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(context,"không có internet",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
    }
}
