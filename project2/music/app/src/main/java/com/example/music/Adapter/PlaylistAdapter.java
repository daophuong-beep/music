package com.example.music.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.music.Model.Playlist;
import com.example.music.R;
import com.example.music.Service.DiaChiIP;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

class ViewHolder{
    TextView tvNoiDungItemPlayList;
    ImageView ivIconItemPlayList;
    ImageView ivItemPlayList;
}
public class PlaylistAdapter extends ArrayAdapter<Playlist>{

    public PlaylistAdapter(@NonNull Context context, int resource, @NonNull List<Playlist> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView == null){
            LayoutInflater layoutInflater=LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.dong_playlist,null);
            viewHolder = new ViewHolder();
            viewHolder.ivIconItemPlayList=convertView.findViewById(R.id.iviconitemplaylist);
            viewHolder.ivItemPlayList=convertView.findViewById(R.id.ivitemplaylist);
            viewHolder.tvNoiDungItemPlayList=convertView.findViewById(R.id.tvnoidung_itemplaylist);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();

        }
        Playlist playlist= getItem(position);
        Picasso.with(getContext()).load(DiaChiIP.ip +playlist.getHinhPlayList()).into(viewHolder.ivItemPlayList);
        Picasso.with(getContext()).load(DiaChiIP.ip +playlist.getIcon()).into(viewHolder.ivIconItemPlayList);
        viewHolder.tvNoiDungItemPlayList.setText(playlist.getTen());
        return convertView;
    }
}
//public class PlaylistAdapter extends BaseAdapter {
//    ArrayList<Playlist> arrayPlayList;
//
//    public PlaylistAdapter(ArrayList<Playlist> arrayPlayList) {
//        this.arrayPlayList = arrayPlayList;
//    }
//
//    @Override
//    public int getCount() {
//        return arrayPlayList.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return arrayPlayList.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        LayoutInflater inflater= .getLayoutInflater();
//        View view =inflater.inflate(R.layout.dong_playlist,null);
//        ViewHolder viewHolder = new ViewHolder();
//        viewHolder.ivIconItemPlayList=view.findViewById(R.id.iviconitemplaylist);
//        viewHolder.ivItemPlayList=view.findViewById(R.id.ivitemplaylist);
//        viewHolder.tvNoiDungItemPlayList=view.findViewById(R.id.tvnoidung_itemplaylist);
//
//        //viewHolder.ivItemPlayList.;
//        Picasso.with().load(arrayPlayList.get(position).getHinhPlayList()).into(viewHolder.ivItemPlayList);
//        Picasso.with().load(arrayPlayList.get(position).getIcon()).into(viewHolder.ivIconItemPlayList);
//        viewHolder.tvNoiDungItemPlayList.setText(arrayPlayList.get(position).getTen());
//        return view;
//    }
//
//}
