package com.example.music.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.music.Activity.DanhSachBaiHatActivity;
import com.example.music.Activity.DanhSachChuDeActivity;
import com.example.music.Activity.DanhSachTheLoaiTheoChuDeActivity;
import com.example.music.Model.Chude;
import com.example.music.Model.Chudetheloai;
import com.example.music.Model.Theloai;
import com.example.music.R;
import com.example.music.Service.APIService;
import com.example.music.Service.Dataservice;
import com.example.music.Service.DiaChiIP;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Chude_Theloai_Today extends Fragment {
    View view;
    HorizontalScrollView horizontalScrollView;
    TextView tvXemThem;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chude_theloai_trongngay,container,false);
        horizontalScrollView=view.findViewById(R.id.hschudetheloai);
        tvXemThem=view.findViewById(R.id.tvxemthem);
        getData();
        tvXemThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DanhSachChuDeActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
    private void getData(){
        Dataservice dataservice= APIService.getService();
        Call<Chudetheloai> callBack=dataservice.getCateGoryMusic();
        callBack.enqueue(new Callback<Chudetheloai>() {
            @Override
            public void onResponse(Call<Chudetheloai> call, Response<Chudetheloai> response) {
                Chudetheloai chudetheloai= response.body();
//                Log.d("AAAA",chudetheloai.getChude().get(0).getTenChuDe());

                final ArrayList<Chude> chudeArrayList=new ArrayList<>();
                chudeArrayList.addAll(chudetheloai.getChude());

                final ArrayList<Theloai> theloaiArrayList=new ArrayList<>();
                theloaiArrayList.addAll(chudetheloai.getTheloai());

                LinearLayout linearLayout= new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(500,250);
                layoutParams.setMargins(10,10,10 ,10);
                for(int i=0;i<chudeArrayList.size();i++){
                    CardView cardView=new CardView(getActivity());
                    cardView.setRadius(20);
                    ImageView imageView=new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if(chudeArrayList.get(i).getHinhChuDe()!=null){
                        Picasso.with(getActivity()).load(DiaChiIP.ip+chudeArrayList.get(i).getHinhChuDe()).into(imageView);
                    }

                    final int position=i;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent= new Intent(getActivity(), DanhSachTheLoaiTheoChuDeActivity.class);
                            intent.putExtra("chude",chudeArrayList.get(position));
                            startActivity(intent);
                        }
                    });
                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                }
                for(int j=0;j<theloaiArrayList.size();j++){
                    CardView cardView=new CardView(getActivity());
                    cardView.setRadius(20);
                    ImageView imageView=new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if(theloaiArrayList.get(j).getHinhTheLoai()!=null){
                        Picasso.with(getActivity()).load(DiaChiIP.ip+theloaiArrayList.get(j).getHinhTheLoai()).into(imageView);
                    }
                    final int fj=j;// chuyển j về biến final
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent= new Intent(getActivity(), DanhSachBaiHatActivity.class);
                            intent.putExtra("itemtheloai",theloaiArrayList.get(fj));
                            startActivity(intent);
                        }
                    });

                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                }
                horizontalScrollView.addView(linearLayout);
            }

            @Override
            public void onFailure(Call<Chudetheloai> call, Throwable t) {
                Log.d("AAAA","lỗi");
            }
        });
    }
}
