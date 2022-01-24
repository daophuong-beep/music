package com.example.music.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.music.Adapter.DanhSachBaiHatAdapter;
import com.example.music.Model.Album;
import com.example.music.Model.BaiHat;
import com.example.music.Model.Playlist;
import com.example.music.Model.Quangcao;
import com.example.music.Model.Theloai;
import com.example.music.R;
import com.example.music.Service.APIService;
import com.example.music.Service.Dataservice;
import com.example.music.Service.DiaChiIP;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Url;

public class DanhSachBaiHatActivity extends AppCompatActivity {
    private Quangcao quangcao;
    private Playlist playlist;
    private Theloai theloai;
    private Album album;


    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    RecyclerView rvdanhsach;
    FloatingActionButton floatingActionButton;
    Toolbar toolbar;
    ImageView ivdanhsachbaihat;

    ArrayList<BaiHat> arrayListBaihat;

    DanhSachBaiHatAdapter danhSachBaiHatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_bai_hat);
        anhXa();
        init();
        dateIntent();


    }

    private void dateIntent() {
        Intent intent=getIntent();
        if(intent!=null){
            if(intent.hasExtra("banner")){
                quangcao= (Quangcao) intent.getSerializableExtra("banner");
                Toast.makeText(this,quangcao.getTenbaihat().toString()+" của bannner",Toast.LENGTH_LONG).show();
                getDataQuangCao(quangcao.getIdquangcao());
                setValueInView(quangcao.getTenbaihat(),DiaChiIP.ip+quangcao.getHinhbaihat() );
                eventClick();
            }
            else if(intent.hasExtra("itemplaylist")){
                Toast.makeText(this,"thành công rồi",Toast.LENGTH_LONG).show();
                playlist=(Playlist) intent.getSerializableExtra("itemplaylist");
                getDataPlayList(playlist.getIdPlayList());
                setValueInView(playlist.getTen(),DiaChiIP.ip+ playlist.getIcon());
                eventClick();
            }
            else if(intent.hasExtra("itemtheloai")){
                Toast.makeText(this,"thành công rồi",Toast.LENGTH_LONG).show();
                theloai=(Theloai) intent.getSerializableExtra("itemtheloai");
                setValueInView(theloai.getTenTheLoai(),DiaChiIP.ip+theloai.getHinhTheLoai());
                getDataTheLoai(theloai.getIdTheLoai());
                eventClick();
            }
            else if(intent.hasExtra("itemalbum")){
                Toast.makeText(this,"thành công rồi",Toast.LENGTH_LONG).show();
                album=(Album) intent.getSerializableExtra("itemalbum");
                setValueInView(album.getTenAlbum(),DiaChiIP.ip+album.getHinhAlbum());
                getDataAlbum(album.getIdAlbum());
                eventClick();
            }
        }
    }

    private void eventClick(){
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DanhSachBaiHatActivity.this,PlayNhacActivity.class);
                intent.putExtra("danhsachcakhuc",arrayListBaihat);
                startActivity(intent);
            }
        });
    }
    private void getDataAlbum(String idAlbum) {
        Dataservice dataservice=APIService.getService();
        Call<List<BaiHat>> callBack= dataservice.getDanhSachBaiHatTheoAlbum(idAlbum);
        callBack.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                arrayListBaihat= (ArrayList<BaiHat>) response.body();
                danhSachBaiHatAdapter=new DanhSachBaiHatAdapter(DanhSachBaiHatActivity.this,arrayListBaihat);
                rvdanhsach.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                rvdanhsach.setAdapter(danhSachBaiHatAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void getDataTheLoai(String idTheLoai) {
        Dataservice dataservice=APIService.getService();
        Call<List<BaiHat>> callBack= dataservice.getDanhSachBaiHatTheoTheLoai(idTheLoai);
        callBack.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                arrayListBaihat= (ArrayList<BaiHat>) response.body();
                danhSachBaiHatAdapter=new DanhSachBaiHatAdapter(DanhSachBaiHatActivity.this,arrayListBaihat);
                rvdanhsach.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                rvdanhsach.setAdapter(danhSachBaiHatAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void getDataPlayList(String idPlayList) {
        Dataservice dataservice=APIService.getService();
        Call<List<BaiHat>> callBack= dataservice.getDanhSachBaiHatTheoPlayList(idPlayList);
        callBack.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                arrayListBaihat= (ArrayList<BaiHat>) response.body();
                danhSachBaiHatAdapter=new DanhSachBaiHatAdapter(DanhSachBaiHatActivity.this,arrayListBaihat);
                rvdanhsach.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                rvdanhsach.setAdapter(danhSachBaiHatAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void getDataQuangCao(String idQuangCao) {
        Dataservice dataservice= APIService.getService();
        Call<List<BaiHat>> callBack= dataservice.getDanhSachBaiHatTheoQuangCao(idQuangCao);
        callBack.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                arrayListBaihat= (ArrayList<BaiHat>) response.body();
                danhSachBaiHatAdapter=new DanhSachBaiHatAdapter(DanhSachBaiHatActivity.this,arrayListBaihat);
                rvdanhsach.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                rvdanhsach.setAdapter(danhSachBaiHatAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }


    private void setValueInView(String ten,String hinh) {
        collapsingToolbarLayout.setTitle(ten);
        URL url= null;
        try {
            url = new URL(hinh);
            Bitmap bitmap= BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable=new BitmapDrawable(getResources(),bitmap);

            collapsingToolbarLayout.setBackground(bitmapDrawable);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Picasso.with(this).load(hinh).into(ivdanhsachbaihat);
    }

    private void init() {
//        setSupportActionBar(toolbar);
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE); // gán màu chũ phần title là màu trắng
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);

        // khỏi tạo ban đầu cho nút fab là false sau click thành true
        floatingActionButton.setEnabled(false);
    }

    private void anhXa() {
        coordinatorLayout=findViewById(R.id.coordinatorlayout);
        collapsingToolbarLayout=findViewById(R.id.collapsingtoolbar);
        rvdanhsach=findViewById(R.id.rvdanhsach);
        floatingActionButton=findViewById(R.id.fab);
//        toolbar=findViewById(R.id.toolbardanhsach);
        ivdanhsachbaihat=findViewById(R.id.ivdanhsachbaihat);
    }


}