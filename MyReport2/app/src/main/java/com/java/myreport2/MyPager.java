package com.java.myreport2;

import android.content.Context;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.google.android.material.tabs.TabItem;

public class MyPager extends FragmentStatePagerAdapter {

    private int pageCounter;
    private AdapterView.OnItemClickListener myOnItemClickListener;

    public MyPager(FragmentManager fm, int pageCounter) {
        super(fm);
        this.pageCounter = pageCounter;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Tab_Item1 tab_item1 = new Tab_Item1();
                return tab_item1;
            case 1:
                Tab_Item2 tab_item2 = new Tab_Item2();
                return tab_item2;
            case 2:
                Tab_Item3 tab_item3 = new Tab_Item3();
                return tab_item3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return pageCounter;
    }
}
