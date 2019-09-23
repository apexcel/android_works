package com.java.schprj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class week_4_lecture extends AppCompatActivity {

    EditText editText1, editText2;
    Button btnPlus, btnMinus, btnMultiply, btnDivide;
    TextView answer;
    String num1, num2;
    Integer intAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_4_lecture);
        editText1 = (EditText) findViewById(R.id.textFirst);
        editText2 = (EditText) findViewById(R.id.textSecond);
        answer = (TextView) findViewById(R.id.textView3);

        btnPlus = (Button) findViewById(R.id.plusBtn);
        btnMinus = (Button) findViewById(R.id.subtractBtn);
        btnMultiply = (Button) findViewById(R.id.multiplyBtn);
        btnDivide = (Button) findViewById(R.id.divideBtn);

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = editText1.getText().toString();
                num2 = editText1.getText().toString();
                intAnswer = Integer.parseInt(num1) + Integer.parseInt(num2);
                answer.setText("Answer : " + intAnswer.toString());
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = editText1.getText().toString();
                num2 = editText1.getText().toString();
                intAnswer = Integer.parseInt(num1) - Integer.parseInt(num2);
                answer.setText("Answer : " + intAnswer.toString());
            }
        });

        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = editText1.getText().toString();
                num2 = editText1.getText().toString();
                intAnswer = Integer.parseInt(num1) * Integer.parseInt(num2);
                answer.setText("Answer : " + intAnswer.toString());
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = editText1.getText().toString();
                num2 = editText1.getText().toString();
                intAnswer = Integer.parseInt(num1) / Integer.parseInt(num2);
                answer.setText("Answer : " + intAnswer.toString());
            }
        });
    }
}
