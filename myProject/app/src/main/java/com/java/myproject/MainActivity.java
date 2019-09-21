package com.java.myproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar headTb = findViewById(R.id.head_toolbar);
        setSupportActionBar(headTb);
        ActionBar headAb = getSupportActionBar();
        headAb.setTitle("Title");


    }

    private void toastMsg(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.headermenu, menu);

        return true;
    }

    //TODO: onClick & onClickListener 구현하기
    // drawer 구현
    // menuitemclicklistener();
    // icons add

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search :
                ((TextView)findViewById(R.id.textView)).setText("Search");
                toastMsg("");
                return true;
            case R.id.more :
                ((TextView)findViewById(R.id.textView)).setText("More");
                toastMsg("");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
