package com.java.fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    MainFragment frag1;
    MenuFragment frag2;

    Button btn;
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frag1 = new MainFragment();
        frag2 = new MenuFragment();

        btn = (Button) findViewById(R.id.showMainFrag);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, frag1).commit();
            }
        });

        btn2 = (Button) findViewById(R.id.showMenuFrag);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, frag2).commit();
            }
        });
    }

    public void onFragmentChange(int idx) {
        if (idx == 0) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, frag1).commit();
        }
        else if (idx == 1) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, frag2).commit();
        }
    }
}
