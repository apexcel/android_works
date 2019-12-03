package com.example.projectt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ShowWordsFragment extends Fragment {

    ArrayList<String> wordList;
    ArrayList<String> meanList;

    ListView wordsList;

    MyAdapter myAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = (View) inflater.inflate(R.layout.fragment_show_words, container, false);

        wordList = new ArrayList<>();
        meanList = new ArrayList<>();

        myAdapter = new MyAdapter();
        wordsList = (ListView) rootView.findViewById(R.id.show_word_list);
        wordsList.setAdapter(myAdapter);

        String texts = readTextFile(MyValues.path, MyValues.tempFileName);
        inspectWord(texts, wordList, meanList);

        // TODO: 이 프래그먼트에서 메인액티비티로 데이터 넘겨주기 이후에 받은 데이터로 뷰페이저 실행
        for (int i = 0; i < wordList.size(); i++) {
            myAdapter.addData(wordList.get(i), meanList.get(i));
        }

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
