package com.example.projectt;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class TestActivity extends AppCompatActivity {

    Toolbar tb;
    String fileName;

    ArrayList words, means;
    ArrayList<MyQuestionClass> qWords = new ArrayList<>();

    TextView counter, showWord, correctCounter, wrongCounter;
    Button opt1, opt2, opt3, opt4;

    Chronometer chronometer;

    Animation expand;
    Animation shrink;

    ProgressBar progressBar;
    int progressValue = 5;

    int count;
    int correct, wrong;
    long eTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        initializeToolbar();
        setVar();

        Intent mIntent = getIntent();
        String data = mIntent.getStringExtra("DATA");
        fileName = MyValues.sendFilename;
        MyValues.sendFilename = "";
        inspectWord(data, words, means);

        expand = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.expansion);
        shrink = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.return_to_normal);

        chronometer.setBase(SystemClock.elapsedRealtime());

        Random rnd = new Random();
        correct = 0;
        wrong = 0;

        if (words.size() < 15) {
            alertWarn();
        }

        else {

            for (int i = 0; i < 15; i++) {
                int random = rnd.nextInt(words.size() - i);
                int random2 = rnd.nextInt(words.size() - 3);
                if (random + 3 > words.size()) {
                    random = rnd.nextInt(words.size() - 3);
                }
                if (i == 0 || i == 2 || i == 6 || i == 14) {
                    qWords.add(new MyQuestionClass(words.get(random).toString(), means.get(random).toString(), means.get(random2).toString(), means.get(random + 1).toString(), means.get(random + 2).toString(), means.get(random).toString()));
                } else if (i == 3 || i == 7 || i == 13) {
                    qWords.add(new MyQuestionClass(words.get(random).toString(), means.get(random + 1).toString(), means.get(random).toString(), means.get(random2).toString(), means.get(random + 2).toString(), means.get(random + 2).toString()));
                } else if (i == 5 || i == 9 || i == 11) {
                    qWords.add(new MyQuestionClass(words.get(random).toString(), means.get(random + 1).toString(), means.get(random2 + 1).toString(), means.get(random).toString(), means.get(random + 2).toString(), means.get(random).toString()));
                } else {
                    qWords.add(new MyQuestionClass(words.get(random).toString(), means.get(random + 2).toString(), means.get(random + 1).toString(), means.get(random2).toString(), means.get(random).toString(), means.get(random).toString()));
                }
            }

            count = 0;
            loadQuestions(count);

            chronometer.start();
        }
    }

    private void inspectWord(String line, ArrayList wordList, ArrayList meanList) {
        String temp = "";

        for (int i = 0; i < line.length(); i++) {
            if (!String.valueOf('|').equals(String.valueOf(line.charAt(i))) && !String.valueOf('/').equals(String.valueOf(line.charAt(i)))) {
                temp += line.charAt(i);
            }
            else if(String.valueOf('|').equals(String.valueOf(line.charAt(i)))) {
                wordList.add(temp);
                temp = "";
            }
            else if (String.valueOf('/').equals(String.valueOf(line.charAt(i)))) {
                meanList.add(temp);
                temp = "";
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent homeIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(homeIntent);
                return true;
        }
        return false;
    }

    public void initializeToolbar() {
        tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("Test");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setVar() {
        words = new ArrayList();
        means = new ArrayList();
        counter = (TextView) findViewById(R.id.counter);
        showWord = (TextView) findViewById(R.id.showWord);
        opt1 = (Button) findViewById(R.id.opt_btn1);
        opt2 = (Button) findViewById(R.id.opt_btn2);
        opt3 = (Button) findViewById(R.id.opt_btn3);
        opt4 = (Button) findViewById(R.id.opt_btn4);
        chronometer = (Chronometer) findViewById(R.id.chronometer);
        correctCounter = (TextView) findViewById(R.id.cor_counter);
        wrongCounter = (TextView) findViewById(R.id.wrong_counter);
        progressBar = (ProgressBar) findViewById(R.id.progress);
    }

    public void loadQuestions(int i) {
        final MyQuestionClass q = qWords.get(i);

        counter.setText(i + "/" + qWords.size());

        counter.setText("#" + (i + 1));
        showWord.setText("" + q.getWord());
        opt1.setText("" + q.getOpt1());
        opt2.setText("" + q.getOpt2());
        opt3.setText("" + q.getOpt3());
        opt4.setText("" + q.getOpt4());

        opt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (opt1.getText().equals(q.getCorrectOpt())) {
                    Snackbar.make(v, "Correct!", Snackbar.LENGTH_SHORT).show();
                    if (count < qWords.size() - 1) {
                        count++;
                        correct++;
                        correctCounter.setText("" + correct + " / 15");
                        setCorrectAnimation();
                        loadQuestions(count);
                    }
                    else {
                        if (count < 15) {
                            count++;
                            chronometer.stop();
                            correct++;
                            correctCounter.setText("" + correct + " / 15");
                            Snackbar.make(v, "Test Completed!", Snackbar.LENGTH_SHORT).show();
                            eTime = ((SystemClock.elapsedRealtime() - chronometer.getBase())/1000);
                            saveData();
                        }
                        else {
                            Snackbar.make(v, "Test Completed!", Snackbar.LENGTH_SHORT).show();
                            eTime = ((SystemClock.elapsedRealtime() - chronometer.getBase())/1000);
                            saveData();
                        }
                    }
                }
                else {
                    if (count < qWords.size() - 1) {
                        Snackbar.make(v, "Wrong Answer!", Snackbar.LENGTH_SHORT).show();
                        wrong++;
                        count++;
                        wrongCounter.setText("" + wrong + " / 15");
                        setWrongAnimation();
                        loadQuestions(count);
                    }
                    else {
                        if (count < 15) {
                            count++;
                            chronometer.stop();
                            wrong++;
                            wrongCounter.setText("" + wrong + " / 15");
                            Snackbar.make(v, "Test Completed!", Snackbar.LENGTH_SHORT).show();
                            eTime = ((SystemClock.elapsedRealtime() - chronometer.getBase())/1000);
                            saveData();
                        }
                        else {
                            Snackbar.make(v, "Test Completed!", Snackbar.LENGTH_SHORT).show();
                            eTime = ((SystemClock.elapsedRealtime() - chronometer.getBase())/1000);
                            saveData();
                        }
                    }
                }
            }
        });

        opt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (opt2.getText().equals(q.getCorrectOpt())) {
                    Snackbar.make(v, "Correct!", Snackbar.LENGTH_SHORT).show();
                    if (count < qWords.size() - 1) {
                        count++;
                        correct++;
                        correctCounter.setText("" + correct + " / 15");
                        setCorrectAnimation();
                        loadQuestions(count);
                    }
                    else {
                        if (count < 15) {
                            count++;
                            chronometer.stop();
                            correct++;
                            correctCounter.setText("" + correct + " / 15");
                            Snackbar.make(v, "Test Completed!", Snackbar.LENGTH_SHORT).show();
                            eTime = ((SystemClock.elapsedRealtime() - chronometer.getBase())/1000);
                            saveData();
                        }
                        else {
                            Snackbar.make(v, "Test Completed!", Snackbar.LENGTH_SHORT).show();
                            eTime = ((SystemClock.elapsedRealtime() - chronometer.getBase())/1000);
                            saveData();
                        }
                    }
                }
                else {
                    if (count < qWords.size() - 1) {
                        Snackbar.make(v, "Wrong Answer!", Snackbar.LENGTH_SHORT).show();
                        wrong++;
                        count++;
                        wrongCounter.setText("" + wrong + " / 15");
                        setWrongAnimation();
                        loadQuestions(count);
                    }
                    else {
                        if (count < 15) {
                            count++;
                            chronometer.stop();
                            wrong++;
                            wrongCounter.setText("" + wrong + " / 15");
                            Snackbar.make(v, "Test Completed!", Snackbar.LENGTH_SHORT).show();
                            eTime = ((SystemClock.elapsedRealtime() - chronometer.getBase())/1000);
                            saveData();
                        }
                        else {
                            Snackbar.make(v, "Test Completed!", Snackbar.LENGTH_SHORT).show();
                            eTime = ((SystemClock.elapsedRealtime() - chronometer.getBase())/1000);
                            saveData();
                        }
                    }
                }
            }
        });

        opt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (opt3.getText().equals(q.getCorrectOpt())) {
                    Snackbar.make(v, "Correct!", Snackbar.LENGTH_SHORT).show();
                    if (count < qWords.size() - 1) {
                        count++;
                        correct++;
                        correctCounter.setText("" + correct + " / 15");
                        setCorrectAnimation();
                        loadQuestions(count);
                    }
                    else {
                        if (count < 15) {
                            count++;
                            chronometer.stop();
                            correct++;
                            correctCounter.setText("" + correct + " / 15");
                            Snackbar.make(v, "Test Completed!", Snackbar.LENGTH_SHORT).show();
                            eTime = ((SystemClock.elapsedRealtime() - chronometer.getBase())/1000);
                            saveData();
                        }
                        else {
                            Snackbar.make(v, "Test Completed!", Snackbar.LENGTH_SHORT).show();
                            eTime = ((SystemClock.elapsedRealtime() - chronometer.getBase())/1000);
                            saveData();
                        }
                    }
                }
                else {
                    if (count < qWords.size() - 1) {
                        Snackbar.make(v, "Wrong Answer!", Snackbar.LENGTH_SHORT).show();
                        wrong++;
                        count++;
                        wrongCounter.setText("" + wrong + " / 15");
                        setWrongAnimation();
                        loadQuestions(count);
                    }
                    else {
                        if (count < 15) {
                            count++;
                            chronometer.stop();
                            wrong++;
                            wrongCounter.setText("" + wrong + " / 15");
                            Snackbar.make(v, "Test Completed!", Snackbar.LENGTH_SHORT).show();
                            eTime = ((SystemClock.elapsedRealtime() - chronometer.getBase())/1000);
                            saveData();
                        }
                        else {
                            Snackbar.make(v, "Test Completed!", Snackbar.LENGTH_SHORT).show();
                            eTime = ((SystemClock.elapsedRealtime() - chronometer.getBase())/1000);
                            saveData();
                        }
                    }
                }
            }
        });

        opt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (opt4.getText().equals(q.getCorrectOpt())) {
                    Snackbar.make(v, "Correct!", Snackbar.LENGTH_SHORT).show();
                    if (count < qWords.size() - 1) {
                        count++;
                        correct++;
                        correctCounter.setText("" + correct + " / 15");
                        setCorrectAnimation();
                        loadQuestions(count);
                    }
                    else {
                        if (count < 15) {
                            count++;
                            chronometer.stop();
                            correct++;
                            correctCounter.setText("" + correct + " / 15");
                            Snackbar.make(v, "Test Completed!", Snackbar.LENGTH_SHORT).show();
                            eTime = ((SystemClock.elapsedRealtime() - chronometer.getBase())/1000);
                            saveData();
                        }
                        else {
                            Snackbar.make(v, "Test Completed!", Snackbar.LENGTH_SHORT).show();
                            eTime = ((SystemClock.elapsedRealtime() - chronometer.getBase())/1000);
                            saveData();
                        }
                    }
                }
                else {
                    if (count < qWords.size() - 1) {
                        Snackbar.make(v, "Wrong Answer!", Snackbar.LENGTH_SHORT).show();
                        wrong++;
                        count++;
                        wrongCounter.setText("" + wrong + " / 15");
                        setWrongAnimation();
                        loadQuestions(count);
                    }
                    else {
                        if (count < 15) {
                            count++;
                            chronometer.stop();
                            wrong++;
                            wrongCounter.setText("" + wrong + " / 15");
                            Snackbar.make(v, "Test Completed!", Snackbar.LENGTH_SHORT).show();
                            eTime = ((SystemClock.elapsedRealtime() - chronometer.getBase())/1000);
                            saveData();
                        }
                        else {
                            Snackbar.make(v, "Test Completed!", Snackbar.LENGTH_SHORT).show();
                            eTime = ((SystemClock.elapsedRealtime() - chronometer.getBase())/1000);
                            saveData();
                        }
                    }
                }
            }
        });
    }

    public void saveData() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(TestActivity.this);
        builder.setTitle("시험 결과를 저장하시겠습니까?");

        final Intent homeIntent = new Intent(getApplicationContext(), MainActivity.class);
        long time = System.currentTimeMillis();
        SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        final String date = dayTime.format(new Date(time));

        builder.setPositiveButton(R.string.str_confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                makeTextFile(MyValues.path, date + fileName, "맞은 개수 : " + correct + "\n틀린 개수 : " + wrong + "\n걸린 시간 : " + eTime);
                startActivity(homeIntent);
            }
        });

        builder.setNegativeButton(R.string.str_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(homeIntent);
            }
        });

        builder.show();
    }

    public void makeTextFile(String path, String fileName, String data) {
        File textFile = new File(path + "/" + fileName);

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path + "/" + fileName, true));
            bufferedWriter.flush();
            bufferedWriter.write(data);
            bufferedWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void alertWarn() {
        AlertDialog.Builder builder = new AlertDialog.Builder(TestActivity.this);
        builder.setTitle("경고!");
        builder.setMessage("해당 단어 세트의 등록된 단어의 개수가 15개 이상이어야 가능합니다!");

        builder.setPositiveButton(R.string.str_confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent homeIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(homeIntent);
            }
        });

        builder.show();
    }

    private void setCorrectAnimation() {
        correctCounter.setTextColor(getResources().getColor(R.color.colorPrimary));
        correctCounter.startAnimation(expand);
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        correctCounter.setTextColor(getResources().getColor((R.color.colorText)));
                    }
                }, 550);
        correctCounter.startAnimation(shrink);
        progressValue += 5;
        progressBar.setProgress(progressValue);
    }

    private void setWrongAnimation() {
        wrongCounter.setTextColor(getResources().getColor(R.color.colorWrongText));
        wrongCounter.startAnimation(expand);
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        wrongCounter.setTextColor(getResources().getColor((R.color.colorText)));
                    }
                }, 550);
        wrongCounter.startAnimation(shrink);
        progressValue += 5;
        progressBar.setProgress(progressValue);
    }
}
