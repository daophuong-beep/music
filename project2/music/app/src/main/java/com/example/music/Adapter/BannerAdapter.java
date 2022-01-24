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
import androidx.viewpager.widget.PagerAdapter;

import com.example.music.Activity.DanhSachBaiHatActivity;
import com.example.music.Model.Quangcao;
import com.example.music.R;
import com.example.music.Service.DiaChiIP;
import com.squareup.picasso.Picasso;

import java.io.ObjectInputValidation;
import java.util.ArrayList;

public class BannerAdapter extends PagerAdapter {
    private Context context;
    private ArrayList<Quangcao> arrayListBanner;

    public BannerAdapter(Context context, ArrayList<Quangcao> arrayListBanner) {
        this.context = context;
        this.arrayListBanner = arrayListBanner;
    }

    @Override
    public int getCount() {
        return arrayListBanner.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dong_banner,null);
        ImageView imageViewBanner= view.findViewById(R.id.imageViewBanner);
        ImageView ivicon=view.findViewById(R.id.ivicon_banner);
        TextView tvbaihat=view.findViewById(R.id.tvbaihat_banner);
        TextView tvnoidung=view.findViewById(R.id.tvnoidung_banner);

        Picasso.with(context).load(DiaChiIP.ip+arrayListBanner.get(position).getHinhanh()).into(imageViewBanner);
        Picasso.with(context).load(DiaChiIP.ip+arrayListBanner.get(position).getHinhbaihat()).into(ivicon);
        tvbaihat.setText(arrayListBanner.get(position).getTenbaihat());
        tvnoidung.setText(arrayListBanner.get(position).getNoidung());
        container.addView(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context,tvbaihat.getText().toString(),Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context, DanhSachBaiHatActivity.class);
                intent.putExtra("banner",arrayListBanner.get(position));
                context.startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
