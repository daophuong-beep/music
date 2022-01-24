package com.example.music.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.music.Adapter.TheLoaiAdapter;
import com.example.music.Model.Chude;
import com.example.music.Model.Theloai;
import com.example.music.R;
import com.example.music.Service.APIService;
import com.example.music.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachTheLoaiTheoChuDeActivity extends AppCompatActivity {
private Chude chude;
RecyclerView rvTheLoai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_the_loai_theo_chu_de);
        Intent intent= getIntent();
        chude=(Chude) intent.getSerializableExtra("chude");
        rvTheLoai=findViewById(R.id.rvtheloai);
        getData();
    }

    private void getData() {
        Dataservice dataservice= APIService.getService();
        Call<List<Theloai>> callBack= dataservice.getDanhSachTheLoaiTheoChuDe(chude.getIdChuDe());
        callBack.enqueue(new Callback<List<Theloai>>() {
            @Override
            public void onResponse(Call<List<Theloai>> call, Response<List<Theloai>> response) {
                ArrayList<Theloai> arrayListTheLoai= (ArrayList<Theloai>) response.body();
                TheLoaiAdapter theLoaiAdapter=new TheLoaiAdapter(DanhSachTheLoaiTheoChuDeActivity.this,arrayListTheLoai);


                rvTheLoai.setLayoutManager(new GridLayoutManager(DanhSachTheLoaiTheoChuDeActivity.this,2));
                rvTheLoai.setAdapter(theLoaiAdapter);
            }

            @Override
            public void onFailure(Call<List<Theloai>> call, Throwable t) {

            }
        });
    }
}