package com.example.projectt;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ListViewFragment extends Fragment {

    ListView listView;
    ArrayList<String> fileList;
    ArrayList<String> tempFileList;
    ArrayAdapter<String> arrayAdapter;
    AddWordSetFragment addWordSetFragment;

    String param1;
    String param2;

    public static ListViewFragment getInstance() {
        return new ListViewFragment();
    }

    ListViewFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = (View) inflater.inflate(R.layout.fragment_list_view, null);


        if (!MyValues.lines.equals("") && !MyValues.wordSetName.equals("")) {
            makeTextFile(MyValues.path, MyValues.wordSetName, MyValues.lines);
        }

        Toast.makeText(((MainActivity)getActivity()).getApplicationContext(), MyValues.lines, Toast.LENGTH_SHORT).show();

        addWordSetFragment = new AddWordSetFragment();

        String rootDir = MyValues.path;
        File files = new File(rootDir);
        File[] filesList = files.listFiles();

        listView = (ListView) rootView.findViewById(R.id.list_view);
        fileList = new ArrayList<>();
        tempFileList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, fileList);
        listView.setAdapter(arrayAdapter);

        for (int i = 0; i < filesList.length; i++) {
            tempFileList.add(filesList[i].getName());
        }

        for (int i = 0; i < tempFileList.size(); i++) {
            if (!fileList.contains(tempFileList.get(i))) {
                fileList.add(tempFileList.get(i));
            }
            else {
            }
        }
        arrayAdapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                parent.getItemAtPosition(position);
                Toast.makeText(getActivity(), ""+parent.getItemIdAtPosition(position), Toast.LENGTH_SHORT).show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                int i = (int)parent.getItemIdAtPosition(position);
                String name = fileList.get(i);
                File temp = new File(MyValues.path, name);
                temp.delete();
                Snackbar.make(getActivity().findViewById(android.R.id.content), name + " 삭제되었습니다.", Snackbar.LENGTH_LONG).show();

                for (int j = 0; j < tempFileList.size(); j++) {
                    if (!fileList.contains(tempFileList.get(j))) {
                        fileList.add(tempFileList.get(j));
                    }
                    else {
                    }
                }
                arrayAdapter.notifyDataSetChanged();

                return true;
            }
        });

        ImageButton addWordSetBtn = (ImageButton) rootView.findViewById(R.id.add_word_set_btn);
        addWordSetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).replaceFragment(AddWordSetFragment.getInstance());
                MyValues.wordSetName = "";
                MyValues.lines = "";
            }
        });


        return rootView;
    }

    public void makeTextFile(String path, String fileName, String data) {
        File textFile = new File(path + "/" + fileName + ".txt");

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path + "/" + fileName + ".txt", true));
            bufferedWriter.flush();
            bufferedWriter.write(data);
            bufferedWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
