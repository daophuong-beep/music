package com.example.music.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.music.Model.Chude;
import com.example.music.R;
import com.example.music.Service.DiaChiIP;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
public class ChuDeAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Chude> mangChuDe;

    public ChuDeAdapter(Context context, ArrayList<Chude> mangChuDe) {
        this.context = context;
        this.mangChuDe = mangChuDe;
    }

    @Override
    public int getCount() {
        return mangChuDe.size();
    }

    @Override
    public Object getItem(int position) {
        return mangChuDe.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_chude,parent,false);
        ImageView ivChuDe=view.findViewById(R.id.ivchude);
        Picasso.with(context).load(DiaChiIP.ip+mangChuDe.get(position).getHinhChuDe()).into(ivChuDe);
        return view;
    }
}
