package com.java.midexam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.KeyEventDispatcher;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.net.URI;

public class MainActivity extends AppCompatActivity {


    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.exam1);
        btn2 = (Button) findViewById(R.id.exam2);
        btn3 = (Button) findViewById(R.id.exam3);
        btn4 = (Button) findViewById(R.id.exam4);
        btn5 = (Button) findViewById(R.id.exam5);
        btn6 = (Button) findViewById(R.id.exam6);
        btn7 = (Button) findViewById(R.id.exam7);
        btn8 = (Button) findViewById(R.id.exam8);

        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.exam1:
                        Intent intent1 = new Intent(getApplicationContext(), exam1.class);
                        startActivity(intent1);
                        break;
                    case R.id.exam2:
                        Intent intent2 = new Intent(getApplicationContext(), exam2.class);
                        startActivity(intent2);
                        break;
                    case R.id.exam3:
                        Intent intent3 = new Intent(getApplicationContext(), exam3.class);
                        startActivity(intent3);
                        break;
                    case R.id.exam4:
                        Intent intent4 = new Intent(getApplicationContext(), exam4.class);
                        startActivity(intent4);
                        break;
                    case R.id.exam5:
                        Intent intent5 = new Intent(getApplicationContext(), exam5.class);
                        startActivity(intent5);
                        break;
                    case R.id.exam6:
                        Intent intent6 = new Intent(getApplicationContext(), exam6.class);
                        startActivity(intent6);
                        break;
                    case R.id.exam7:
                        Intent intent7 = new Intent(getApplicationContext(), exam7.class);
                        startActivity(intent7);
                        break;
                    case R.id.exam8:
                        Intent intent8 = new Intent(getApplicationContext(), exam8.class);
                        startActivity(intent8);
                        break;

                }
            }
        };
        btn1.setOnClickListener(onClickListener);
        btn2.setOnClickListener(onClickListener);
        btn3.setOnClickListener(onClickListener);
        btn4.setOnClickListener(onClickListener);
        btn5.setOnClickListener(onClickListener);
        btn6.setOnClickListener(onClickListener);
        btn7.setOnClickListener(onClickListener);
        btn8.setOnClickListener(onClickListener);
    }
}
