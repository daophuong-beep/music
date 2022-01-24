package com.example.music.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.music.Adapter.BaiHatHotAdapter;
import com.example.music.Model.BaiHat;
import com.example.music.R;
import com.example.music.Service.APIService;
import com.example.music.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_BaiHat_Hot extends Fragment {
    View view;
    RecyclerView recyclerViewBaiHatHot;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_baihat_hot,container,false);
        recyclerViewBaiHatHot=view.findViewById(R.id.rvbaihat);
        getData();
        return view;
    }

    private void getData() {
        Dataservice dataservice= APIService.getService();
        Call<List<BaiHat>> callBack=dataservice.getBaiHatYeuThich();
        callBack.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                ArrayList<BaiHat> arrayListBaiHat= (ArrayList<BaiHat>) response.body();
                //Log.d("BBBB",arrayListBaiHat.get(0).getTenBaiHat());
                BaiHatHotAdapter baiHatHotAdapter=new BaiHatHotAdapter(getActivity(),arrayListBaiHat);
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewBaiHatHot.setLayoutManager(linearLayoutManager);
                recyclerViewBaiHatHot.setAdapter(baiHatHotAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }
}
