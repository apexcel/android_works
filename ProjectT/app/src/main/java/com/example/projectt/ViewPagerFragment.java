package com.example.projectt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ViewPagerFragment extends Fragment {

    ArrayList<String> wordList;
    ArrayList<String> meanList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = (View) inflater.inflate(R.layout.fragment_view_pager, container, false);

        wordList = new ArrayList<>();
        meanList = new ArrayList<>();

        String texts = readTextFile(MyValues.path, MyValues.tempFileName);

        TextView showWord = rootView.findViewById(R.id.showWord);
        TextView showMeaning = rootView.findViewById(R.id.showMeaning);
        inspectWord(texts, wordList, meanList);

        showWord.setText(wordList.get(0));
        showMeaning.setText(meanList.get(0));

        return rootView;
    }

    public String readTextFile(String path, String fileName) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            InputStream inputStream = new FileInputStream(path + "/" +fileName);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String _word = "";
            while ((_word = bufferedReader.readLine()) != null) {
                stringBuffer.append(_word +"\n");
            }
            bufferedReader.close();
            inputStream.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return stringBuffer.toString();
    }

    // 문자열 구분 함수
    public void inspectWord(String line, ArrayList wordList, ArrayList meanList) {
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
}
