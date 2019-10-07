package com.java.layouttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Toolbar tb;
    Button toTable, toGrid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tb = (Toolbar) findViewById(R.id.appBar);
        setSupportActionBar(tb);
        tb.setTitle("Week6_1 Practice");


        toTable = (Button) findViewById(R.id.toTable);
        toGrid = (Button) findViewById(R.id.toGrid);

        toTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TableExam.class);
                startActivity(intent);
            }
        });

        toGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GridExam.class);
                startActivity(intent);
            }
        });

    }
}
