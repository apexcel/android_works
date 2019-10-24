package com.java.midexam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class exam4 extends AppCompatActivity {

    Button btn;
    ImageButton imgBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam4);

        btn = (Button) findViewById(R.id.exam4Btn);
        imgBtn = (ImageButton) findViewById(R.id.exam4ImgBtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgBtn.setRotation(imgBtn.getRotation() + 10);
            }
        });
    }
}
