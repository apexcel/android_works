package com.java.layouttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TableExam extends AppCompatActivity {

    final int MAX = 10;
    Button[] numBtns = new Button[MAX];
    Integer[] numBtnsID= {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9,};
    Button btnADD, btnSUBTRACT, btnMULTIPLY, btnDIVIDE;
    EditText input1, input2;
    String num1, num2;
    Integer answerINT;
    TextView answer;
    Toolbar tb;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_exam);

        tb = (Toolbar) findViewById(R.id.appBar);
        tb.setTitle("Table Layout");

        for (i = 0; i < numBtnsID.length; i++) { // 배열이용해서 할당
            numBtns[i] = (Button) findViewById(numBtnsID[i]);
        }

        for (i = 0; i < numBtnsID.length; i++) {
            final int index;
            index = i;

            numBtns[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (input1.isFocused()) {
                        num1 = input1.getText().toString() + numBtns[index].getText().toString();
                        input1.setText(num1);
                    }
                    else if (input2.isFocused()) {
                        num2 = input2.getText().toString() + numBtns[index].getText().toString();
                        input2.setText(num2);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Focus on EditText", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

        btnADD = (Button) findViewById(R.id.add);
        btnSUBTRACT = (Button) findViewById(R.id.subtract);
        btnMULTIPLY = (Button) findViewById(R.id.multiply);
        btnDIVIDE = (Button) findViewById(R.id.divide);

        input1 = (EditText) findViewById(R.id.number1);
        input2 = (EditText) findViewById(R.id.number2);

        answer = (TextView) findViewById(R.id.answer);

        btnADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = input1.getText().toString();
                num2 = input2.getText().toString();
                answerINT = Integer.parseInt(num1) + Integer.parseInt(num2);
                answer.setText(answerINT.toString());
            }
        });

        btnSUBTRACT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = input1.getText().toString();
                num2 = input2.getText().toString();
                answerINT = Integer.parseInt(num1) - Integer.parseInt(num2);
                answer.setText(answerINT.toString());
            }
        });

        btnMULTIPLY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = input1.getText().toString();
                num2 = input2.getText().toString();
                answerINT = Integer.parseInt(num1) * Integer.parseInt(num2);
                answer.setText(answerINT.toString());
            }
        });

        btnDIVIDE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = input1.getText().toString();
                num2 = input2.getText().toString();
                answerINT = Integer.parseInt(num1) / Integer.parseInt(num2);
                answer.setText(answerINT.toString());
            }
        });
    }


}
