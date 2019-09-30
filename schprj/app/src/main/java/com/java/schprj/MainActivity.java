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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar tb = (Toolbar) findViewById(R.id.appToolbar);
        setSupportActionBar(tb);


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
    }

}
