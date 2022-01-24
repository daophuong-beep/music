package com.example.music.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.music.Activity.PlayNhacActivity;
import com.example.music.Adapter.PlayNhacAdapter;
import com.example.music.R;

public class Fragment_Play_DanhSach_Cac_BaiHat extends Fragment {
    View view;
    RecyclerView rvPlayDSBaiHat;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_play_danhsach_cac_baihat,container,false);
        rvPlayDSBaiHat=view.findViewById(R.id.rvplaydanhsachcacbaihat);
        PlayNhacAdapter playNhacAdapter=new PlayNhacAdapter(getContext(), PlayNhacActivity.mangBaiHat);
        rvPlayDSBaiHat.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvPlayDSBaiHat.setAdapter(playNhacAdapter);
        return view;
    }
}
