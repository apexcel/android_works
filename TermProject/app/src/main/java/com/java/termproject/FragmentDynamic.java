package com.java.termproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

        View rootView = (View) inflater.inflate(R.layout.fragment_dynamic, container, false);
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
