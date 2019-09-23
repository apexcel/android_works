package com.java.androidprc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button backToMain = (Button) findViewById(R.id.back2main);
        backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    Intent passedIntent = getIntent();
    processingIntent(passedIntent);
    }

    private void processingIntent(Intent intent) {
        if (intent != null) {
            ArrayList<String> names = (ArrayList<String>) intent.getSerializableExtra("names");
            if (names != null) {
                Toast.makeText(getApplicationContext(), "Passed Names list items count : " + names.size(), Toast.LENGTH_LONG).show();
            }
            SimpleData data = (SimpleData) intent.getParcelableExtra("data");
            if (data != null) {
                Toast.makeText(getApplicationContext(), "Passed Data, Simple Data : " + data.message, Toast.LENGTH_LONG).show();
            }

        }
    }
}
