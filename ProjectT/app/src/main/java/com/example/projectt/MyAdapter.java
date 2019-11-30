package com.example.projectt;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class MyAdapter extends FragmentStatePagerAdapter {


    ArrayList<String> words;
    ArrayList<String> means;
    int index;

    public MyAdapter(FragmentManager fm, int index) {
        super(fm);
        this.index = index;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
