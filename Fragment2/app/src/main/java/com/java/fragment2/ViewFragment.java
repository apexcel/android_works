package com.java.fragment2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ViewFragment extends Fragment {

    ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_viewer, container, false);

        imageView = (ImageView) rootView.findViewById(R.id.imageView);

        return rootView;
    }

    public void setImage(int idx) {
        if (idx == 0) {
            imageView.setImageResource(R.drawable.thor);
        }
        else if (idx == 1) {
            imageView.setImageResource(R.drawable.iron);
        }
        else if (idx == 2) {
            imageView.setImageResource(R.drawable.cap);
        }
    }
}
