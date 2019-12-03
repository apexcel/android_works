package com.example.projectt;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class MyDialogFragment extends DialogFragment {

    private MyDialogListener myDialogListener;

    public static MyDialogFragment newInstance(MyDialogListener listener) {
        MyDialogFragment fragment = new MyDialogFragment();
        fragment.myDialogListener = listener;
        return fragment;
    }

    public interface MyDialogListener {
        public void callback(boolean ans);
    }

    public MyDialogFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            myDialogListener = (MyDialogListener) getTargetFragment();
        }
        catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.fragment_dialog, null)).setPositiveButton(R.string.str_confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myDialogListener.callback(true);
            }
        });

        builder.setView(inflater.inflate(R.layout.fragment_dialog, null)).setNegativeButton(R.string.str_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myDialogListener.callback(false);
            }
        });

        return builder.create();
    }
}