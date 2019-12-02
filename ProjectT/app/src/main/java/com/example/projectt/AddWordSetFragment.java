package com.example.projectt;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class AddWordSetFragment extends Fragment {

    public static AddWordSetFragment getInstance() {
        return new AddWordSetFragment();
    }

    public AddWordSetFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = (View) inflater.inflate(R.layout.fragment_add_word_set, container, false);

        final EditText wordSetName = rootView.findViewById(R.id.word_set_input);
        final EditText word = rootView.findViewById(R.id.word_input);
        final EditText mean = rootView.findViewById(R.id.meaning_input);
        Button addBtn = rootView.findViewById(R.id.add_word_btn);
        Button saveBtn = rootView.findViewById(R.id.save_word_set_btn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!wordSetName.getText().toString().equals("")) {
                    MyValues.wordSetName = wordSetName.getText().toString();
                    Intent homeIntent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                    startActivity(homeIntent);
                }
                else {
                    Toast.makeText(((MainActivity)getActivity()), "값을 입력하세요", Toast.LENGTH_SHORT).show();
                }
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!word.getText().toString().equals("") && !mean.getText().toString().equals((""))) {
                    MyValues.lines += word.getText().toString() + "|" + mean.getText().toString() + "/";
                    word.setText("");
                    mean.setText("");

                    Toast.makeText(((MainActivity)getActivity()), "단어가 추가 되었습니다. 다음 단어를 입력하세요", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(((MainActivity)getActivity()), "값을 모두 입력하세요", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }

}
