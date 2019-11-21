package com.java.termproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SectionIndexer;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Toolbar tb;

    ListView listView;
    ArrayList<String> arrayList; // 폴더
    ArrayAdapter<String> arrayAdapter;
    ArrayList<HashMap<String, String>> itemlist; // 단어
    HashMap<String, String> hashMap = new HashMap<>();

    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeLayout();
        initializeBottomNav();
        requestPermission();
        isExistFolder("");

    }

    // 권한 요청
    public void requestPermission() {
        int permissionReadStrage = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int permissionWriteStorage = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        // 권한이 있으면 PERMISSION_GRANTED 반환 없으면 PERMISSION_DENIED 반환
        if (permissionWriteStorage == PackageManager.PERMISSION_GRANTED && permissionReadStrage == PackageManager.PERMISSION_GRANTED) {

        }
        else {
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 2);
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

    // 툴바 및 기타 초기화
    public void initializeLayout() {

        tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("Home");

        listView = (ListView) findViewById(R.id.list_view);
        arrayList = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);

    }

    // 하단 탭 네비게이션 초기화
    public void initializeBottomNav() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_home:
                        //Intent homeIntent = new Intent(getApplicationContext(), MainActivity.class);
                        getSupportActionBar().setTitle("Home");
                        //startActivity(homeIntent);
                        return true;
                    case R.id.menu_search:
                        getSupportActionBar().setTitle("Search");
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
                    addFolder();
                    return true;
                case R.id.create_word:
                    return true;
            }
            return false;
        }
    };

    // 다이얼로그 띄우고 폴더 생성
    private void addFolder() {
        final EditText folderName = new EditText(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("단어장 폴더 생성");
        builder.setMessage("단어장 폴더의 이름을 입력하세요.");
        builder.setView(folderName);

        builder.setPositiveButton(R.string.str_confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = folderName.getText().toString();
                final File dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), name);

                if (!dir.exists()) { // dir가 존재 하지 않으면 생성
                    dir.mkdir();
                    arrayList.add(counter, name);
                    counter++;
                    isExistFolder(name);
                }
                else { // 이미 존재
                    Toast.makeText(MainActivity.this, "이미 동일한 이름의 폴더가 존재합니다.", Toast.LENGTH_LONG).show();
                    isExistFolder(name);
                }
            }
        });

        builder.setNegativeButton(R.string.str_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });


        /* 실제 폴더 생성 관련
                File dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), wordInput.getText().toString());
                if (!dir.mkdir()) {
                    Toast.makeText(MainActivity.this, "폴더 생성 실패", Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "---- 폴더 생성 실패 ----");
                }
                else {
                    Toast.makeText(MainActivity.this, wordInput.getText().toString() + " 폴더가 생성되었습니다", Toast.LENGTH_SHORT).show();
                }
                 */

        builder.show();
    }

    // 폴더 불러오기
    public void isExistFolder(String name) {
        String rootDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File file = new File(rootDir);
        File[] fileList = file.listFiles();

        List<String> fileNameList = new ArrayList<>();

            for (int i = 0; i < fileList.length; i++) {
                fileNameList.add(fileList[i].getName());

                if (name.equals("")) {
                    Toast.makeText(this, "폴더 불러오기" + fileList[i].getName(), Toast.LENGTH_SHORT).show();
                }
                else {
                    arrayList.add(fileList[i].getName());
                    counter++;
                    Toast.makeText(this, counter + "이다", Toast.LENGTH_SHORT).show();
                }
            }
    }
}
