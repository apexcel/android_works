package com.example.projectt;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Context
    public static Context mainContext;

    // 툴바
    Toolbar tb;

    // 프래그먼트
    WebViewFragment webViewFragment;
    WordListFragment wordListFragment;

    // 시험보기
    final int[] selectedItem = {0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainContext = this;

        webViewFragment = new WebViewFragment();
        wordListFragment = new WordListFragment();

        initializeToolbar();
        requestPermission();
        initializeBottomNav();

        File dir = new File(MyValues.testPath);
        if (!dir.exists()) {
            dir.mkdir();
        }

        ImageView imageView = (ImageView)findViewById(R.id.image_view);
        Glide.with(this).load(R.raw.ss).into(imageView);

    }


    // 권한요청
    public void requestPermission() {
        int permissionReadStorage = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int permissionWriteStorage = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permissionInternet = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET);

        // 권한이 있으면 PERMISSION_GRANTED 반환 없으면 PERMISSION_DENIED 반환
        if (permissionWriteStorage == PackageManager.PERMISSION_GRANTED
                && permissionReadStorage == PackageManager.PERMISSION_GRANTED
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

    // 툴바 초기화
    public void initializeToolbar() {
        tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("Home");
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
                    case R.id.menu_edit:
                        Intent editIntent = new Intent(getApplicationContext(), EditActivity.class);
                        startActivity(editIntent);
                        return true;
                    case R.id.menu_search:
                        getSupportActionBar().setTitle("Search");
                        replaceFragment(webViewFragment);
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.info:
                infoDialog();
                break;
            case R.id.test:
                testDialog();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void infoDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setView(R.layout.myinfo_modal);

        builder.setPositiveButton(R.string.str_confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }

    public void testDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("시험 단어장을 고르세요");
        getFile();
        final String[] item = new String[getFile().size()];

        for (int i = 0; i < getFile().size(); i++) {
            item[i] = getFile().get(i);
        }
        builder.setSingleChoiceItems(item, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectedItem[0] = which;
            }
        });

        final String name = item[selectedItem[0]].replace("/storage/emulated/0/Download/", "");
        builder.setPositiveButton(R.string.str_confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, item[selectedItem[0]], Toast.LENGTH_SHORT).show();
                MyValues.testLines = readTextFile(item[selectedItem[0]]);
                MyValues.sendFilename = name;
                Intent testIntent = new Intent(getApplicationContext(), TestActivity.class);
                testIntent.putExtra("DATA", MyValues.testLines);
                MyValues.testLines = "";
                startActivity(testIntent);
            }
        });

        builder.setNegativeButton(R.string.str_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
            }
        });

        builder.show();
    }

    private ArrayList<String> getFile() {
        File dir = new File(MyValues.path);
        File[] childFiles = dir.listFiles();
        ArrayList<String> items = new ArrayList<>();

        for (int i = 0; i < childFiles.length; i++) {
            items.add(i, childFiles[i].toString());
        }

        return items;
    }

    public String readTextFile(String path) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            InputStream inputStream = new FileInputStream(path);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String _word = "";
            while ((_word = bufferedReader.readLine()) != null) {
                stringBuffer.append(_word +"\n");
            }
            bufferedReader.close();
            inputStream.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return stringBuffer.toString();
    }

}
