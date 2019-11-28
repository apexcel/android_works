package com.example.projectt;

import java.io.Serializable;

public class Item implements Serializable {
    public int index;
    public String word;
    public String meaning;

    public Item(int index, String word, String meaning) {
        this.index = index;
        this.word = word;
        this.meaning = meaning;
    }
}

