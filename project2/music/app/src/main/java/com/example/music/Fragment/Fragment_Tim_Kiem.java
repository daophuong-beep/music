package com.example.music.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.music.Adapter.SeachBaiHatAdapter;
import com.example.music.Model.BaiHat;
import com.example.music.R;
import com.example.music.Service.APIService;
import com.example.music.Service.Dataservice;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Tim_Kiem extends Fragment {
    private Toolbar toolbar;
    private RecyclerView rvDanhSachBaiHatTimKiem;
    private TextView tvKhongTimThay;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_tim_kiem,container,false);
        toolbar=view.findViewById(R.id.toolbartimkiem);
        rvDanhSachBaiHatTimKiem=view.findViewById(R.id.rvplaydanhsachcacbaihat);
        tvKhongTimThay=view.findViewById(R.id.tvkhongtimthay);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("");
        setHasOptionsMenu(true);
        return view;
    }

    private void seachBaiHat(String tuKhoa){
        if(tuKhoa.equals("")){
//            Toast.makeText(getContext(),"xóa",Toast.LENGTH_SHORT).show();
            rvDanhSachBaiHatTimKiem.setVisibility(View.GONE);
        }
        else{
            Dataservice dataservice= APIService.getService();
            Call<List<BaiHat>> callBack=dataservice.getDanhSachTimKiem(tuKhoa);
            callBack.enqueue(new Callback<List<BaiHat>>() {
                @Override
                public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                    ArrayList<BaiHat> arrayListBaiHat= (ArrayList<BaiHat>) response.body();
                    if(arrayListBaiHat.size()>0){
                        SeachBaiHatAdapter seachBaiHatAdapter=new SeachBaiHatAdapter(getContext(),arrayListBaiHat);
                        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
                        rvDanhSachBaiHatTimKiem.setLayoutManager(linearLayoutManager);
                        rvDanhSachBaiHatTimKiem.setAdapter(seachBaiHatAdapter);
                        tvKhongTimThay.setVisibility(View.GONE);
                        rvDanhSachBaiHatTimKiem.setVisibility(View.VISIBLE);
                    }else{
                        tvKhongTimThay.setVisibility(View.VISIBLE);
                        rvDanhSachBaiHatTimKiem.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Call<List<BaiHat>> call, Throwable t) {

                }
            });
        }

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_seach,menu);
        MenuItem menuItem= menu.findItem(R.id.menu_seach);
        SearchView searchView=(SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("BBBB","thanh công");
                seachBaiHat(newText);
                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }
}
