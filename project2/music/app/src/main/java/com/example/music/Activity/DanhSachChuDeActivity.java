package com.example.music.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.music.Adapter.ChuDeAdapter;
import com.example.music.Model.Chude;
import com.example.music.R;
import com.example.music.Service.APIService;
import com.example.music.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachChuDeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_chu_de);

        Dataservice dataservice= APIService.getService();
        Call<List<Chude>> callBack= dataservice.getDanhSachChuDe();
        callBack.enqueue(new Callback<List<Chude>>() {
            @Override
            public void onResponse(Call<List<Chude>> call, Response<List<Chude>> response) {
                ArrayList<Chude> arrayListChuDe= (ArrayList<Chude>) response.body();
                ListView lvChuDe= findViewById(R.id.lvchude);
                ChuDeAdapter chuDeAdapter=new ChuDeAdapter(DanhSachChuDeActivity.this,arrayListChuDe);
                lvChuDe.setAdapter(chuDeAdapter);
                lvChuDe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(DanhSachChuDeActivity.this,DanhSachTheLoaiTheoChuDeActivity.class);
                        intent.putExtra("chude",arrayListChuDe.get(position));
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Chude>> call, Throwable t) {

            }
        });


    }
}