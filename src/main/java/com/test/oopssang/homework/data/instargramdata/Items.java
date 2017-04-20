package com.test.oopssang.homework.data.instargramdata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by sang on 2017-04-19.
 *  * Instartgram   media Api의 responce가 저장될 VO
 */

public class Items {
    private ArrayList<Item> items;

    @SerializedName("more_available")
    @Expose
    private boolean more_available;

    public void setItem(ArrayList<Item> item) {
        this.items = item;
    }

    public ArrayList<Item> getItem() {
        return this.items;
    }

    public boolean isMore_available() {
        return more_available;
    }

    public void setMore_available(boolean more_available) {
        this.more_available = more_available;
    }
}
