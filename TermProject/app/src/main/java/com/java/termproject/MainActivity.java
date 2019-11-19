package com.java.termproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    Toolbar tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeLayout();
        initializeBottomNav();

    }

    // 툴바 초기화
    public void initializeLayout() {

        tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("Home");
    }

    // 하단 탭 네비게이션 초기화
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
                        return true;
                    case R.id.menu_edit:
                        PopupMenu popupMenu = new PopupMenu(getApplicationContext(), getWindow().getDecorView().getRootView().findViewById(R.id.menu_edit));
                        popupMenu.inflate(R.menu.creates);
                        popupMenu.setOnMenuItemClickListener(mPopupMenuClick);
                        popupMenu.show();
                        return true;
                    case R.id.menu_setting:
                        return true;
                }
                return false;
            }
        });
    }

    // 팝업 메뉴 클릭 커스텀 이벤트
    PopupMenu.OnMenuItemClickListener mPopupMenuClick = new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.create_folder:
                    dialogShow();
                    return true;
                case R.id.create_word:
                    return true;
            }
            return false;
        }
    };

    // 커스텀 다이얼로그
    private void dialogShow() {
        final EditText editText = new EditText(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.create_folder);
        builder.setMessage(R.string.input_folder_name);
        builder.setView(editText);
        builder.setPositiveButton(R.string.str_confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                File dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), editText.getText().toString());
                if (!dir.mkdir()) {
                    Toast.makeText(MainActivity.this, "폴더 생성 실패", Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "---- 폴더 생성 실패 ----");
                }
                else {
                    Toast.makeText(MainActivity.this, editText.getText().toString() + " 폴더가 생성되었습니다", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton(R.string.str_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });

        builder.show();
    }
}
