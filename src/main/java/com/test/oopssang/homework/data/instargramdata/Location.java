package com.test.oopssang.homework.data.instargramdata;

/**
 * Created by sang on 2017-04-14.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("name")
    @Expose
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}