package com.java.myreport2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class ReportActivity extends AppCompatActivity {

    MyPager myPager;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        tabLayout = (TabLayout) findViewById(R.id.tabsLayout);

        myPager = new MyPager(getSupportFragmentManager(), tabLayout.getTabCount()); // 커스텀 뷰 페이저
        viewPager = (ViewPager) findViewById(R.id.viewPager); // 뷰 페이저 등록
        viewPager.setAdapter(myPager); // 뷰 페이저에 커스텀 뷰 페이저 등록
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout)); // 탭 레이아웃에 뷰 페이저 등록
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { // 탭 레이아웃 이벤트 등록
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
