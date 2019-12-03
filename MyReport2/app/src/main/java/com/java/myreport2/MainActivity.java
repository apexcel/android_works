package com.java.myreport2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar tb;

    TextView intro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeLayout();
        drawerLayout.openDrawer(Gravity.LEFT);
    }


    public void initializeLayout() {
        drawerLayout = findViewById(R.id.drawerMain);
        //navigationView = (NavigationView) findViewById(R.id.navView);
        //navigationView.setNavigationItemSelectedListener(navListener);

        intro = (TextView) findViewById(R.id.intro);

        tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("201332020_최형욱");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 액션바 홈아이콘 활성화
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_reorder_24px); // 홈 아이콘 변경
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // 액션바 아이템 추가
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { // 액션바 Item 셀렉터

        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
            case R.id.tb_more:
                Toast.makeText(this, "Please Add Menu", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    NavigationView.OnNavigationItemSelectedListener navListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.toDrawerHome:
                    Intent homeIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(homeIntent);
                    break;
            }
            drawerLayout.closeDrawers();
            return true;
        }
    };

}
