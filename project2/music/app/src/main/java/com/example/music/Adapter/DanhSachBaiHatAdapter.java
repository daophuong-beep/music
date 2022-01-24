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

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachBaiHatAdapter extends RecyclerView.Adapter<DanhSachBaiHatAdapter.ViewHolder>{
    Context context;
    ArrayList<BaiHat> mangBaiHat;

    public DanhSachBaiHatAdapter(Context context, ArrayList<BaiHat> mangBaiHat) {
        this.context = context;
        this.mangBaiHat = mangBaiHat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dong_danh_sach_bai_hat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat=mangBaiHat.get(position);
        holder.tvtenbaihat.setText(baiHat.getTenBaiHat());
        holder.tvcasi.setText(baiHat.getCaSi());
        holder.tvindex.setText(String.valueOf(position+1));

    }

    @Override
    public int getItemCount() {
        return mangBaiHat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvindex,tvtenbaihat,tvcasi;
        ImageView ivluotthich;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvindex = itemView.findViewById(R.id.tvsothutu);
            tvcasi=itemView.findViewById(R.id.tvtencasi);
            tvtenbaihat=itemView.findViewById(R.id.tvtenbaihat);
            ivluotthich=itemView.findViewById(R.id.ivluotthich);

            ivluotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(context, tvTenBaiHat.getText().toString(),Toast.LENGTH_SHORT).show();

                    Dataservice dataservice= APIService.getService();
                    Call<String> callBack=dataservice.updateLuotThich("1",mangBaiHat.get(getPosition()).getIdBaiHat());
                    callBack.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua= response.body();
                            if(ketqua.equals("success")){
                                ivluotthich.setImageResource(R.drawable.iconloved);
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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc",mangBaiHat.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
