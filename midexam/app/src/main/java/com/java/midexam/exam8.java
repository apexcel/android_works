package com.java.midexam;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class exam8 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_exam8);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );

        LinearLayout parent = new LinearLayout(this);
        LinearLayout l1 = new LinearLayout(this);
        LinearLayout l2 = new LinearLayout(this);

        parent.setOrientation(LinearLayout.VERTICAL);
        parent.setBackgroundColor(Color.parseColor("#00b248"));

        setContentView(parent, params);

        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        p.weight = 1;

        l1.setOrientation(LinearLayout.VERTICAL);
        l1.setLayoutParams(p);
        l1.setBackgroundColor(Color.parseColor("#ffffff"));
        parent.addView(l1, params);

        l2.setOrientation(LinearLayout.VERTICAL);
        l2.setLayoutParams(p);
        l1.setBackgroundColor(Color.parseColor("#555555"));
        parent.addView(l2, params);
    }
}
