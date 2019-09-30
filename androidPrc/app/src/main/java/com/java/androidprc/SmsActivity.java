package com.java.androidprc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SmsActivity extends AppCompatActivity {

    EditText sender1;
    EditText contents1;
    EditText receiver1;
    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        sender1 = (EditText) findViewById(R.id.sender1);
        contents1 = (EditText) findViewById(R.id.contents1);
        receiver1 = (EditText) findViewById(R.id.receiver1);
        confirm = (Button) findViewById(R.id.confirm);


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                finish();
            }
        });

        Intent passedIntent = getIntent();
        processCommand(passedIntent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        processCommand(intent);
        super.onNewIntent(intent);
    }

    private void processCommand(Intent intent) {
        if (intent != null) {
            String sender = intent.getStringExtra("sender");
            String contents = intent.getStringExtra("contents");
            String receiveDate = intent.getStringExtra("receiveDate");

            sender1.setText(sender);
            contents1.setText(contents);
            receiver1.setText(receiveDate);
        }
    }
}
