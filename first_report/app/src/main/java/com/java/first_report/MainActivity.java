package com.java.first_report;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.*;
import java.lang.String;

public class MainActivity extends AppCompatActivity {

    RadioButton rbuttonA;
    RadioButton rbuttonB;
    RadioButton rbuttonSamsung;
    RadioButton rbuttonApple;
    RadioButton rbuttonEtc;
    RadioButton rbuttonEasy;
    RadioButton rbuttonNormal;
    RadioButton rbuttonHard;

    ImageView myimg;

    TextView result;
    String myStr;
    String myPhone;
    String myDiff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RadioGroup rOne = (RadioGroup) findViewById(R.id.radioGroup);
        final RadioGroup rTwo = (RadioGroup) findViewById(R.id.radioGroup2);
        final RadioGroup rThree = (RadioGroup) findViewById(R.id.radioGroup3);

        rbuttonA = (RadioButton) findViewById(R.id.rBtnA);
        rbuttonB = (RadioButton) findViewById(R.id.rBtnB);
        rbuttonSamsung = (RadioButton) findViewById(R.id.rBtnSamsung);
        rbuttonEtc = (RadioButton) findViewById(R.id.rBtnEtc);
        rbuttonApple = (RadioButton) findViewById(R.id.rBtnApple);
        rbuttonEasy = (RadioButton) findViewById(R.id.easy);
        rbuttonNormal = (RadioButton) findViewById(R.id.normal);
        rbuttonHard = (RadioButton) findViewById(R.id.hard);

        final EditText name = (EditText) findViewById(R.id.name);
        final EditText stuId = (EditText) findViewById(R.id.studentID);
        result = (TextView) findViewById(R.id.result);
        myimg = (ImageView) findViewById(R.id.imageView);

        rbuttonB.setChecked(true);
        rbuttonSamsung.setChecked(true);
        rbuttonNormal.setChecked(true);

        Button btnconfirm = (Button) findViewById(R.id.confirm);



        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        tb.setTitle("Introduce 201332020 최형욱");
        setSupportActionBar(tb);

        result.setVisibility(View.INVISIBLE);
        myimg.setVisibility(View.INVISIBLE);

        btnconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioBtnChecked(rOne);
                radioBtnChecked2(rTwo);
                radioBtnChecked3(rThree);
                result.setText(myStr + " " + myPhone + " " + myDiff + " " + name.getText() + " " + stuId.getText());

                result.setVisibility(View.VISIBLE);
                myimg.setVisibility(View.VISIBLE);

            }
        });
    }

    public void radioBtnChecked(View v) {
        if (rbuttonA.isChecked()) {
            myStr = "Class A";
        }
        else if (rbuttonB.isChecked()) {
            myStr = "Class B";
        }
        else {
            myStr = "";
        }
    }

    public void radioBtnChecked2(View v) {
        if (rbuttonSamsung.isChecked()) {
            myPhone = "Samsung";
        }
        else if (rbuttonApple.isChecked()) {
            myPhone = "Apple";
        }
        else if (rbuttonEtc.isChecked()) {
            myPhone = "ETC.";
        }
        else {
            myPhone = "";
        }
    }

    public void radioBtnChecked3(View v) {
        if (rbuttonEasy.isChecked()) {
            myDiff = "Easy";
        }
        else if (rbuttonNormal.isChecked()) {
            myDiff = "Normal";
        }
        else if (rbuttonHard.isChecked()) {
            myDiff = "Hard";
        }
        else {
            myDiff = "";
        }
    }
}
