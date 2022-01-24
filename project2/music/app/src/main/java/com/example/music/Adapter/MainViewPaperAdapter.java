package com.example.music.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MainViewPaperAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> arrayFragment=new ArrayList<>();
    private ArrayList<String> arraytitle= new ArrayList<>();
    public MainViewPaperAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return arrayFragment.get(position);
    }

    @Override
    public int getCount() {
        return arrayFragment.size();
    }
    public void addFragment(Fragment fragment,String title) {
        arrayFragment.add(fragment);
        arraytitle.add(title);
    }
}
