package com.java.withoutxml;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button btn;
    EditText eTxt;
    TextView txtV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );

        LinearLayout baseLayout = new LinearLayout(this);
        baseLayout.setOrientation(LinearLayout.VERTICAL);
        baseLayout.setBackgroundColor(Color.parseColor("#00b248"));
        setContentView(baseLayout, params);

        btn = new Button(this);
        btn.setText("This is a Button");
        btn.setBackgroundColor(Color.MAGENTA);
        baseLayout.addView(btn);

        eTxt = new EditText(this);
        eTxt.setHint("Typing Anything!");
        eTxt.setText("");
        baseLayout.addView(eTxt);

        txtV = new TextView(this);
        baseLayout.addView(txtV);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Made by Line Coding", Toast.LENGTH_LONG).show();
                txtV.setText(eTxt.getText());
            }
        });
    }

}
