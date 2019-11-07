package com.java.practice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    Toolbar tb;
    DrawerLayout drawerLayout;
    NavigationView navView;

    FragAdvWidget advfrag;
    FragClocks fragClocks;
    FragChronomerter fragChronomerter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initiateLayout();
    }

    private void initiateLayout() {
        tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb); // 액션바로 등록

        advfrag = new FragAdvWidget();
        fragClocks = new FragClocks();
        fragChronomerter = new FragChronomerter();

        getSupportActionBar().setTitle("Main");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 액션바 홈아이콘 활성화
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_reorder_24px); // 홈 아이콘 변경

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_main);
        navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(myNavListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // 툴바에 메뉴 등록
        getMenuInflater().inflate(R.menu.toolbar_menus, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { // 액션바 클릭시

        switch (item.getItemId()) {
            case android.R.id.home: //reorder를 home으로 대체 했으므로 home으로 설정해야함
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
            case R.id.tb_more:
                Toast.makeText(this, "More selected", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // 드로어내부 아이템 클릭시
    NavigationView.OnNavigationItemSelectedListener myNavListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            item.setChecked(true);
            drawerLayout.closeDrawers();

            switch (item.getItemId()) { // 아이템 아이디
                case R.id.toHome:
                    Intent intenthome = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intenthome);
                    return true;
                case R.id.item1:
                    tb.setTitle("Advanced Widget 1");
                    onFragmentChange(0);
                    return true;
                case R.id.item2:
                    tb.setTitle("Advanced Widget 2");
                    onFragmentChange(1);
                    return true;
            }
            return true;
        }
    };

    public void onFragmentChange(int idx) {

        switch (idx) {
            case 0:
                getSupportFragmentManager().beginTransaction().replace(R.id.viewFragMain, advfrag).commit();
                break;
            case 11:
                getSupportFragmentManager().beginTransaction().replace(R.id.showWidget1, fragClocks).commit();
                break;
            case 12:
                getSupportFragmentManager().beginTransaction().replace(R.id.showWidget1, fragChronomerter).commit();
                break;
        }
    }
}
