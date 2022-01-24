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
import java.util.zip.Inflater;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeachBaiHatAdapter extends RecyclerView.Adapter<SeachBaiHatAdapter.ViewHolder> {

    private Context context;
    private ArrayList<BaiHat> danhSachBaiHat;

    public SeachBaiHatAdapter(Context context, ArrayList<BaiHat> danhSachBaiHat) {
        this.context = context;
        this.danhSachBaiHat = danhSachBaiHat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dong_seach_baihat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTenBaiHat.setText(danhSachBaiHat.get(position).getTenBaiHat());
        holder.tvTenCaSi.setText(danhSachBaiHat.get(position).getCaSi());
        Picasso.with(context).load(DiaChiIP.ip +danhSachBaiHat.get(position).getHinhBaiHat()).into(holder.ivHinhBaiHat);

    }

    @Override
    public int getItemCount() {
        return danhSachBaiHat.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTenCaSi,tvTenBaiHat;
        private ImageView ivIconLove,ivHinhBaiHat;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenCaSi=itemView.findViewById(R.id.tvtencasi);
            tvTenBaiHat=itemView.findViewById(R.id.tvtenbaihat);
            ivHinhBaiHat=itemView.findViewById(R.id.ivbaihat);
            ivIconLove=itemView.findViewById(R.id.iviconlove);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc",danhSachBaiHat.get(getPosition()));
                    context.startActivity(intent);
                }
            });
            ivIconLove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(context, tvTenBaiHat.getText().toString(),Toast.LENGTH_SHORT).show();

                    Dataservice dataservice= APIService.getService();
                    Call<String> callBack=dataservice.updateLuotThich("1",danhSachBaiHat.get(getPosition()).getIdBaiHat());
                    callBack.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua= response.body();
                            if(ketqua.equals("success")){
                                ivIconLove.setImageResource(R.drawable.iconloved);
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
