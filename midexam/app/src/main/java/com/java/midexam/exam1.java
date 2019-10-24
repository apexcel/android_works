package com.java.midexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class exam1 extends AppCompatActivity {

    Button btn1, btn2;
    RadioGroup rg;
    RadioButton rbtn1, rbtn2;
    EditText editText;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam1);

        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        rg = (RadioGroup) findViewById(R.id.radioGroup);
        rbtn1 = (RadioButton) findViewById(R.id.radioButton1);
        rbtn2 = (RadioButton) findViewById(R.id.radioButton2);
        editText = (EditText) findViewById(R.id.editText);
        img = (ImageView) findViewById(R.id.imageView);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), editText.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(editText.getText().toString()));
                startActivity(intent);
            }
        });

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (rbtn1.isChecked()) {
                    img.setImageResource(R.drawable.ad);
                }
                else if (rbtn2.isChecked()) {
                    img.setImageResource(R.drawable.ap);
                }
            }
        });
    }
}
