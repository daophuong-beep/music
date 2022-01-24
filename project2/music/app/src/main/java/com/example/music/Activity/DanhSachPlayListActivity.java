package com.example.music.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.music.Adapter.DanhSachPlayListAdapter;
import com.example.music.Model.BaiHat;
import com.example.music.Model.Playlist;
import com.example.music.R;
import com.example.music.Service.APIService;
import com.example.music.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachPlayListActivity extends AppCompatActivity {
RecyclerView rvDanhSachPlayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_play_list);
        anhxa();
        getData();

    }

    private void anhxa() {
        rvDanhSachPlayList=findViewById(R.id.rvplaylist);
    }

    private void getData() {
        Dataservice dataservice= APIService.getService();
        Call<List<Playlist>> callBack = dataservice.getDanhSachPlayList();
        callBack.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                ArrayList<Playlist> danhSachPlayList=(ArrayList<Playlist>) response.body();
                DanhSachPlayListAdapter danhSachPlayListAdapter=new DanhSachPlayListAdapter(DanhSachPlayListActivity.this,danhSachPlayList);
                rvDanhSachPlayList.setLayoutManager(new GridLayoutManager(DanhSachPlayListActivity.this,2));
                rvDanhSachPlayList.setAdapter(danhSachPlayListAdapter);
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });

    }
}