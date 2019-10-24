package com.java.midexam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class exam2 extends AppCompatActivity {

    CheckBox chk1, chk2, chk3;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam2);

        chk1 = (CheckBox) findViewById(R.id.enabled);
        chk2 = (CheckBox) findViewById(R.id.clickable);
        chk3 = (CheckBox) findViewById(R.id.rotation);
        btn = (Button) findViewById(R.id.exam2Btn);


        CheckBox.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if (chk1.isChecked()) {
                        btn.setEnabled(true);
                    }
                    else {
                        btn.setEnabled(false);
                    }
                    if (chk2.isChecked()) {
                        btn.setClickable(true);
                    }
                    else {
                        btn.setClickable(false);
                    }
                    if (chk3.isChecked()) {
                        btn.setRotationY(45);
                    }
                    else {
                        btn.setRotationY(0);
                    }
            }
        };

        chk1.setOnCheckedChangeListener(onCheckedChangeListener);
        chk2.setOnCheckedChangeListener(onCheckedChangeListener);
        chk3.setOnCheckedChangeListener(onCheckedChangeListener);
    }
}
