package com.example.projectt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.projectt.DataList.DataList;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    private ArrayList<DataList> dataList = new ArrayList<>();
    private DataList listViewItem;
    public MyAdapter() {

    }

    public void addData(String eng, String kor) {
        DataList item = new DataList();

        item.setEng(eng);
        item.setKor(kor);

        dataList.add(item);
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();
        CustomViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_list_view, parent, false);

            listViewItem = dataList.get(pos);

            holder = new CustomViewHolder();
            holder.mEng = convertView.findViewById(R.id.eng);
            holder.mKor = convertView.findViewById(R.id.kor);
            convertView.setTag(holder);

            holder.mEng.setText(listViewItem.getEng());
            holder.mKor.setText(listViewItem.getKor());
        }

        else {
            holder = (CustomViewHolder) convertView.getTag();
            holder.mEng.setText(dataList.get(pos).getEng());
            holder.mKor.setText(dataList.get(pos).getKor());
        }

        return convertView;
    }

    public class CustomViewHolder {
        public TextView mEng;
        public TextView mKor;
    }
}
