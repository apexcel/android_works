package com.java.termproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
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
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
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
import java.io.FileOutputStream;
import java.io.IOError;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.HashMap;
import java.util.List;
import java.io.*;

public class MainActivity extends AppCompatActivity {

    // 툴바
    Toolbar tb;

    // 폴더 리스트
    ListView listView;
    ArrayList<String> folderList; // 폴더 리스트
    ArrayList<String> selectedFolderList; // 중복 제거 폴더 리스트
    ArrayAdapter<String> arrayAdapter; // 폴더 리스트 어댑터

    // 단어
    ArrayList<String> word;
    ArrayList<String> meaning;

    // 프래그먼트
    FragmentWebView fragmentWebView;

    // 프래그먼트 동적 추가
    // TODO: 동적 프래그먼트 추가를 함수로 구현해서 폴더 생성시 같이 프래그먼트 생성하도록 하기
    static final String FRAGMENT_TAG = "FRAGMENT_TAG";
    static final String KEYS = "KEYS";
    private int fragmentCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeLayout();
        initializeBottomNav();
        requestPermission();
        refreshFolder();
        listViewAddDelete();

        // 프래그먼트 동적추가
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.addOnBackStackChangedListener(myBackstackListener);
        Fragment fragment = fragmentManager.findFragmentByTag(FRAGMENT_TAG);
        Log.d("MainActivity", "initDynamicFragment = "+ fragment + "fragmentCounter = " + fragmentCounter);
        if (savedInstanceState != null) {
            fragmentManager.beginTransaction().replace(R.id.main_container, FragmentDynamic.getInstance(fragmentCounter), FRAGMENT_TAG).addToBackStack(null).commit();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.removeOnBackStackChangedListener(myBackstackListener);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt(KEYS, fragmentCounter);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        fragmentCounter = savedInstanceState.getInt(KEYS);
    }

    //---------------------------------------------------------------------------------------------------------------------------------------------
    // 권한 요청
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

    //---------------------------------------------------------------------------------------------------------------------------------------------
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

    //---------------------------------------------------------------------------------------------------------------------------------------------
    // 툴바 및 기타 초기화
    public void initializeLayout() {

        tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("Home");

        String rootDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File file = new File(rootDir);
        File[] fileList = file.listFiles();


        listView = (ListView) findViewById(R.id.list_view);
        folderList = new ArrayList<>();
        selectedFolderList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, selectedFolderList);
        listView.setAdapter(arrayAdapter);

        fragmentWebView = new FragmentWebView();

        for (int i = 0; i < fileList.length; i++) {
            folderList.add(fileList[i].getName());
        }

    }

    //---------------------------------------------------------------------------------------------------------------------------------------------
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
                        getSupportActionBar().setTitle("Search");
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragmentWebView).commit();
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

    //---------------------------------------------------------------------------------------------------------------------------------------------
    // 팝업 메뉴 클릭 커스텀 이벤트
    PopupMenu.OnMenuItemClickListener mPopupMenuClick = new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.create_folder:
                    addFolder();
                    return true;
            }
            return false;
        }
    };

    //---------------------------------------------------------------------------------------------------------------------------------------------
    // 다이얼로그 띄우고 폴더 생성
    private void addFolder() {
        final EditText folderName = new EditText(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("단어장 폴더 추가");
        builder.setMessage("폴더의 이름을 입력하세요.");
        builder.setView(folderName);

        builder.setPositiveButton(R.string.str_confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String _folderName = folderName.getText().toString();
                String _fileName = _folderName;
                String _folderPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + _folderName;
                final File dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), _folderName);

                // TODO: 폴더 생성시 같이 동적 프래그먼트 추가하도록 하기, 동적 추가 함수 받아서 실행하도록 하기
                if (!dir.exists()) { // dir가 존재 하지 않으면 생성
                    dir.mkdir();
                    folderList.add(_folderName);
                    writeTextFile(_folderPath, _fileName, "Word|Meaing/Word2|Meaning2/");
                    dynamicFragmentAdding();
                    refreshFolder();
                }
                else { // 이미 존재
                    Toast.makeText(MainActivity.this, "이미 동일한 이름의 폴더가 존재합니다.", Toast.LENGTH_LONG).show();
                    dynamicFragmentDelete();
                    refreshFolder();
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

    // 텍스트 파일 생성
    public void writeTextFile(String folderPath, String fileName, String data) { // 폴더 path와 파일 이름, 데이터를 받아옴

        File textFile = new File(folderPath + "/" + fileName + ".txt");

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(folderPath + "/" + fileName + ".txt", true));
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------------------------
    // 폴더 불러오기
    public void refreshFolder() {

        for (int i = 0; i < folderList.size(); i++) {
            if (!selectedFolderList.contains(folderList.get(i))) {
                selectedFolderList.add(folderList.get(i));
            }
            else {
            }
        }
        arrayAdapter.notifyDataSetChanged();
    }

    //---------------------------------------------------------------------------------------------------------------------------------------------
    // TODO: 프래그먼트 동적 추가
    private FragmentManager.OnBackStackChangedListener myBackstackListener = new FragmentManager.OnBackStackChangedListener() {
        @Override
        public void onBackStackChanged() {
            FragmentManager fragmentManager = getSupportFragmentManager();
            int count = 0;
            for (Fragment f: fragmentManager.getFragments()) {
                if (f != null) {
                    count++;
                }
            }
            fragmentCounter = count;
            Log.d("MainActivity", "onBackStackChanged fragmentCounter = " + fragmentCounter);
        }
    };

    // 프래그먼트 동적 추가
    public void dynamicFragmentAdding() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.main_container, FragmentDynamic.getInstance(fragmentCounter)).addToBackStack(null).commit();
    }

    // 프래그먼트 동적 삭제
    public void dynamicFragmentDelete() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack();
    }

    //---------------------------------------------------------------------------------------------------------------------------------------------
    // 리스트뷰 클릭시 폴더 삭제와 접속 이벤트
    public void listViewAddDelete() {

        // 폴더 리스트 클릭시 이벤트
        // TODO: 프래그먼트 동적 추가 구현한 뒤 리스트 클릭시 해당 프래그먼트로 이동하게 만들기, 해당 부분은 이동 부분
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, selectedFolderList.get(position) + ", " + position, Toast.LENGTH_SHORT).show();
                getSupportActionBar().setTitle(selectedFolderList.get(position));
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container, FragmentDynamic.getInstance(position), FRAGMENT_TAG);
            }
        });

        // 중복 제거된 폴더 리스트 길게 클릭시 삭제 이벤트
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("정말로 폴더를 삭제 하시겠어요?");
                builder.setPositiveButton(R.string.str_confirm, new DialogInterface.OnClickListener() { // 확인
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int _position = position;
                        String name = selectedFolderList.get(_position);
                        final File dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), name);
                        File[] childFiles = dir.listFiles();

                        if (dir.exists()) {
                            for (File child : childFiles) {
                                child.delete();
                            }
                            dir.delete();
                            selectedFolderList.remove(_position);
                            arrayAdapter.notifyDataSetChanged();
                            getSupportActionBar().setTitle("Home");
                        }
                    }
                });
                builder.setNegativeButton(R.string.str_cancel, new DialogInterface.OnClickListener() { // 취소
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();

                return true;
            }
        });
    }
    // 문자열 구분 함수
    public void inspectWord(String line) {

        String temp = "";
        for (int i = 0; i < line.length(); i++) {
            if (!String.valueOf('|').equals(String.valueOf(line.charAt(i))) && !String.valueOf('/').equals(String.valueOf(line.charAt(i)))) {
                temp += line.charAt(i);
            }
            else if(String.valueOf('|').equals(String.valueOf(line.charAt(i)))) {
                word.add(temp);
                temp = "";
            }
            else if (String.valueOf('/').equals(String.valueOf(line.charAt(i)))) {
                meaning.add(temp);
                temp = "";
            }
        }
    }

    // 파일 불러오기
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
