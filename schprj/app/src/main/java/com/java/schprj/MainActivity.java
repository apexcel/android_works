package com.java.schprj;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn, toTestBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar tb = (Toolbar) findViewById(R.id.appToolbar);
        setSupportActionBar(tb);

        toTestBtn = (Button) findViewById(R.id.toTest);
        toTestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), test.class);
                startActivity(intent);
            }
        });

        Button toWeek4 = (Button) findViewById(R.id.toWeek4);
        toWeek4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), week_4_lecture.class);
                startActivity(intent);
            }
        });

        Button toWeek4_2 = (Button) findViewById(R.id.toWeek4_2);
        toWeek4_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), week_4_lecture_2.class);
                startActivity(intent);
            }
        });

        btn = (Button) findViewById(R.id.testOnClick);
    }

    public void toastClick(View v) {
        Toast.makeText(getApplicationContext(), "Custom onClcik Event", Toast.LENGTH_LONG).show(); // 커스텀 onClick으로 XML과 연동하여 사용가능
    }

}
