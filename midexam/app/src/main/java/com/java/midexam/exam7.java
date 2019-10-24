package com.java.midexam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class exam7 extends AppCompatActivity {

    LinearLayout li1, li2, li3, li4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam7);

        li1 = (LinearLayout) findViewById(R.id.l1);
        li2 = (LinearLayout) findViewById(R.id.l2);
        li3 = (LinearLayout) findViewById(R.id.l3);
        li4 = (LinearLayout) findViewById(R.id.l4);

        li1.setFocusable(true);
        li2.setFocusable(true);
        li3.setFocusable(true);
        li4.setFocusable(true);

        li1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), v.getWidth() + " " + v.getHeight(), Toast.LENGTH_LONG).show();
            }
        });
        li2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), v.getWidth() + " " + v.getHeight(), Toast.LENGTH_LONG).show();
            }
        });
        li3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), v.getWidth() + " " + v.getHeight(), Toast.LENGTH_LONG).show();
            }
        });
        li4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), v.getWidth() + " " + v.getHeight(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
