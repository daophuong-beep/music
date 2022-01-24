package com.example.music.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;

import com.example.music.Adapter.MainViewPaperAdapter;
import com.example.music.Fragment.Fragment_Ca_Nhan;
import com.example.music.Fragment.Fragment_Thong_Bao;
import com.example.music.Fragment.Fragment_Tim_Kiem;
import com.example.music.Fragment.Fragment_Trang_Chu;
import com.example.music.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager=findViewById(R.id.myviewpaper);
        tabLayout=findViewById(R.id.mytablayout);

        MainViewPaperAdapter mainViewPaperAdapter = new MainViewPaperAdapter(getSupportFragmentManager());
        mainViewPaperAdapter.addFragment(new Fragment_Trang_Chu(),"trang chu");
        mainViewPaperAdapter.addFragment(new Fragment_Tim_Kiem(),"tim kiem");
//        mainViewPaperAdapter.addFragment(new Fragment_Thong_Bao(),"thông báo");
//        mainViewPaperAdapter.addFragment(new Fragment_Ca_Nhan(),"cá nhân");

        viewPager.setAdapter(mainViewPaperAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        tabLayout.getTabAt(1).setIcon(R.drawable.icontimkiem);
//        tabLayout.getTabAt(2).setIcon(R.drawable.ic_baseline_notifications_24);
//        tabLayout.getTabAt(3).setIcon(R.drawable.ic_baseline_person_24);
    }
}