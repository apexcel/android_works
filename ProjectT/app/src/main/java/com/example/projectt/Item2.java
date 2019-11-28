package com.example.projectt;

import android.os.Parcel;
import android.os.Parcelable;

public class Item2 implements Parcelable {

    public int index;
    public String word; // 주소
    public String meaning;   // 제목

    public Item2(int index, String word, String meaning) {
        this.index = index;
        this.word = word;
        this.meaning = meaning;
    }

    public Item2(Parcel in) {
        index = in.readInt();
        word = in.readString();
        meaning = in.readString();
    }

    public static final Creator<Item2> CREATOR = new Creator<Item2>() {
        @Override
        public Item2 createFromParcel(Parcel in) {
            return new Item2(in);
        }

        @Override
        public Item2[] newArray(int size) {
            return new Item2[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(index);
        dest.writeString(word);
        dest.writeString(meaning);
    }
}
