package com.java.schprj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class week_4_lecture_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_4_lecture_2);

        TextView checkStart = (TextView) findViewById(R.id.checkStart);
        final TextView checkRadio = (TextView) findViewById(R.id.selectAnimal);
        final CheckBox checkBoxStart = (CheckBox) findViewById(R.id.checkBoxStart);
        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.animalGroup);
        RadioButton doggie = (RadioButton) findViewById(R.id.doggie);
        RadioButton cat = (RadioButton) findViewById(R.id.cat);
        RadioButton rabbit = (RadioButton) findViewById(R.id.rabbit);
        final Button confirmAnimal = (Button) findViewById(R.id.confirmAnimal);
        final FrameLayout animalFrame = (FrameLayout) findViewById(R.id.animalFrame);
        final ImageView imgView = (ImageView) findViewById(R.id.catImgView);

        Toolbar tb = (Toolbar) findViewById(R.id.appToolbar);
        setSupportActionBar(tb);

        Button backToMain = (Button) findViewById(R.id.backToMain);
        backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


        checkRadio.setVisibility(View.INVISIBLE);
        radioGroup.setVisibility(View.INVISIBLE);
        confirmAnimal.setVisibility(View.INVISIBLE);
        imgView.setVisibility(View.INVISIBLE);

        checkBoxStart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBoxStart.isChecked()) {
                    checkRadio.setVisibility(View.VISIBLE);
                    radioGroup.setVisibility(View.VISIBLE);
                    confirmAnimal.setVisibility(View.VISIBLE);
                    imgView.setVisibility(View.VISIBLE);
                }
                else {
                    checkRadio.setVisibility(View.INVISIBLE);
                    radioGroup.setVisibility(View.INVISIBLE);
                    confirmAnimal.setVisibility(View.INVISIBLE);
                    imgView.setVisibility(View.INVISIBLE);
                }
            }
        });

        confirmAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.doggie :
                        imgView.setImageResource(R.drawable.doggie);
                        break;
                    case R.id.cat :
                        imgView.setImageResource(R.drawable.cat);
                        break;
                    case R.id.rabbit :
                        imgView.setImageResource(R.drawable.rabbit);
                        break;
                        default:
                            imgView.setVisibility(View.INVISIBLE);
                            break;

                }
            }
        });

    }

}
