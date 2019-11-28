package com.java.termproject;

import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FragmentDynamic extends Fragment {

    private static final String ARG_NO = "ARG_NO";

    public FragmentDynamic() {

    }

    public static FragmentDynamic getInstance(int num) {
        FragmentDynamic fragmentDynamic = new FragmentDynamic();
        Bundle args = new Bundle();
        args.putInt(ARG_NO, num);
        fragmentDynamic.setArguments(args);
        return fragmentDynamic;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView = (View) inflater.inflate(R.layout.fragment_dynamic, container, false);
        Button addBtn = (Button) rootView.findViewById(R.id.add_word);
        Button deleteBtn = (Button) rootView.findViewById(R.id.delete_word);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return rootView;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int no = getArguments().getInt(ARG_NO, 0);
        String text = "" + no + " Fragment";
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView = (TextView) view.findViewById(R.id.text_view);
        int no = getArguments().getInt(ARG_NO, 0);
        String text = "" + no + " Fragment";
        textView.setText(text);
    }

}
