package com.example.music.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.music.Activity.DanhSachBaiHatActivity;
import com.example.music.Adapter.PlaylistAdapter;
import com.example.music.Activity.DanhSachPlayListActivity;
import com.example.music.Model.Playlist;
import com.example.music.R;
import com.example.music.Service.APIService;
import com.example.music.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Playlist extends Fragment {
    View view;
    ListView lvPlaylist;
    TextView tvTitle,tvXemThemPlayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_playlist,container,false);
        // khởi tạo các biến
        lvPlaylist=view.findViewById(R.id.lvplaylist);
        tvTitle=view.findViewById(R.id.tv);
        tvXemThemPlayList=view.findViewById(R.id.tvxemthemplaylist);
        //
        getData();
        return view;
    }
    private void getData(){
        Dataservice dataservice= APIService.getService();
        Call<List<Playlist>> callBack=dataservice.getDataPlayList();
        callBack.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                ArrayList<Playlist> mangPlayList= (ArrayList<Playlist>) response.body();
//                Log.d("BBBB", mangPlayList.get(0).getTen());
                PlaylistAdapter playlistAdapter=new PlaylistAdapter(getActivity(), android.R.layout.simple_list_item_1,mangPlayList);
                lvPlaylist.setAdapter(playlistAdapter);
                lvPlaylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent= new Intent(getContext(), DanhSachBaiHatActivity.class);
                        intent.putExtra("itemplaylist",mangPlayList.get(position));
                        startActivity(intent);
                    }
                });
                tvXemThemPlayList.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent= new Intent(getContext(), DanhSachPlayListActivity.class);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }
}
