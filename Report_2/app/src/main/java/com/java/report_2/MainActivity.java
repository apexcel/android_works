package com.java.report_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    Toolbar tb;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    TabLayout tabLayout;

    Frag_D_Item1 frag_DItem1;
    Frag_D_Item2 frag_DItem2;
    Frag_D_Item3 frag_DItem3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initiateLayout();
    }

    protected void initiateLayout() {
        tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("Main");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 액션바 홈아이콘 활성화
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_reorder_24px); // 홈 아이콘 변경

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerHome);
        navigationView = (NavigationView) findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(navListener);

        frag_DItem1 = new Frag_D_Item1();
        frag_DItem2 = new Frag_D_Item2();
        frag_DItem3 = new Frag_D_Item3();
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
                Toast.makeText(this, "More Button Clicked", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    NavigationView.OnNavigationItemSelectedListener navListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) { // NavigationView 셀렉터
            item.setChecked(true);
            drawerLayout.closeDrawers();

            switch (item.getItemId()) {
                case R.id.toDrawerHome:
                    Intent homeIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(homeIntent);
                    return true;
                case R.id.toReport:
                    Intent reportIntent = new Intent(getApplicationContext(), ReportActivity.class);
                    startActivity(reportIntent);
                    return true;
                case R.id.toDrawerItem1:
                    onChangeFragment(1);
                    return true;
                case R.id.toDrawerItem2:
                    onChangeFragment(2);
                    return true;
                case R.id.toDrawerItem3:
                    onChangeFragment(3);
                    return true;
            }
            return true;

        }
    };

    public void onChangeFragment(int idx) {
        switch (idx) {
            case 1:
                getSupportFragmentManager().beginTransaction().replace(R.id.mainView, frag_DItem1).commit();
                break;
            case 2:
                getSupportFragmentManager().beginTransaction().replace(R.id.mainView, frag_DItem2).commit();
                break;
            case 3:
                getSupportFragmentManager().beginTransaction().replace(R.id.mainView, frag_DItem3).commit();
                break;
        }
    }

}
