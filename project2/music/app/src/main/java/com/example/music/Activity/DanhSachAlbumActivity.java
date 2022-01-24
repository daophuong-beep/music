package com.example.music.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.music.Adapter.ChuDeAdapter;
import com.example.music.Adapter.DanhSachAlbumAdapter;
import com.example.music.Adapter.TheLoaiAdapter;
import com.example.music.Model.Album;
import com.example.music.R;
import com.example.music.Service.APIService;
import com.example.music.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachAlbumActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_album);
        Dataservice dataservice= APIService.getService();
        Call<List<Album>> callBack= dataservice.getDanhSachAlbum();
        callBack.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> albumArrayList= (ArrayList<Album>) response.body();
                RecyclerView rvAlbum=findViewById(R.id.rvalbum);
                DanhSachAlbumAdapter danhSachAlbumAdapter=new DanhSachAlbumAdapter(DanhSachAlbumActivity.this,albumArrayList);


                rvAlbum.setLayoutManager(new GridLayoutManager(DanhSachAlbumActivity.this,2));
                rvAlbum.setAdapter(danhSachAlbumAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }
}