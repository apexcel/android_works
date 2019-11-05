package com.java.fragment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    ListFragment listFragment;
    ViewFragment viewFragment;

    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = getSupportFragmentManager();

        listFragment = (ListFragment) manager.findFragmentById(R.id.listFragment);
        viewFragment = (ViewFragment) manager.findFragmentById(R.id.ViewFragment);

    }

    public void onImgChange(int idx) {
        viewFragment.setImage(idx);
    }
}
