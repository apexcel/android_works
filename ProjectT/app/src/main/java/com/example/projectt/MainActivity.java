package com.example.projectt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    // 툴바
    Toolbar tb;

    // 프래그먼트
    WebViewFragment webViewFragment;
    ListViewFragment listViewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("HOME");

        webViewFragment = new WebViewFragment();
        listViewFragment = new ListViewFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_container, listViewFragment).commit();
        requestPermission();
        initializeBottomNav();
    }

    // 권한요청
    public void requestPermission() {
        int permissionReadStrage = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int permissionWriteStorage = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permissionInternet = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET);

        // 권한이 있으면 PERMISSION_GRANTED 반환 없으면 PERMISSION_DENIED 반환
        if (permissionWriteStorage == PackageManager.PERMISSION_GRANTED
                && permissionReadStrage == PackageManager.PERMISSION_GRANTED
                && permissionInternet == PackageManager.PERMISSION_GRANTED) {

        }
        else {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 2);
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.INTERNET}, 3);
        }
    }

    // 권한 요청 받기
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for (int i = requestCode; i < grantResults.length; i++) {
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
            }
            else if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(this, "권한이 필요합니다.", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "권한이 필요합니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // 바텀 네비게이션 초기화
    public void initializeBottomNav() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_home:
                        Intent homeIntent = new Intent(getApplicationContext(), MainActivity.class);
                        getSupportActionBar().setTitle("Home");
                        startActivity(homeIntent);
                        return true;
                    case R.id.menu_search:
                        getSupportActionBar().setTitle("Search");
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, webViewFragment).commit();
                        return true;
                    case R.id.menu_edit:
                        return true;
                    case R.id.menu_setting:
                        return true;
                }
                return false;
            }
        });
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
