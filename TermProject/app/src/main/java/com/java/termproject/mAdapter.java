package com.java.termproject;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class mAdapter extends PagerAdapter {

    private ArrayList<View> views = new ArrayList<View>();

    @Override
    public int getItemPosition(@NonNull Object object) {
        int index = views.indexOf(object);
        if (index == -1) {
            return POSITION_NONE;
        }
        else {
            return index;
        }
    }

    public int addView(View view) {
        return addView(view, views.size());
    }

    public int addView(View view, int index) {
        views.add(index, view);
        notifyDataSetChanged();
        return index;
    }

    public int removeView(ViewPager pager, View view) {
        notifyDataSetChanged();
        return removeView(pager, views.indexOf(view));
    }

    public int removeView(ViewPager pager, int index) {
        pager.setAdapter(null);
        views.remove(index);
        pager.setAdapter(this);

        return index;
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View v = views.get(position);
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(views.get(position));
    }

    public View getView (int index) {
        return views.get(index);
    }

}
