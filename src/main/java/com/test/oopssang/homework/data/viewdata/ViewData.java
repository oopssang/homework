package com.test.oopssang.homework.data.viewdata;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sang on 2017-04-19.
 */

public class ViewData implements Parcelable {
    private String imageUrl;
    private int width;
    private int heigth;
    private String id;

    public String getImageUrl() {
        return imageUrl;
    }

    public int getWidth() {
        return width;
    }

    public int getHeigth() {
        return heigth;
    }

    public String getId() {
        return id;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeigth(int heigth) {
        this.heigth = heigth;
    }

    public void setId(String id) {
        this.id = id;
    }


    public ViewData() {
    }

    public ViewData(Parcel in) {
        imageUrl = in.readString();
        width = in.readInt();
        heigth = in.readInt();
        id = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imageUrl);
        dest.writeInt(width);
        dest.writeInt(heigth);
        dest.writeString(id);
    }

    public static final Creator<ViewData> CREATOR = new Creator<ViewData>() {
        @Override
        public ViewData createFromParcel(Parcel source) {
            return new ViewData(source);
        }

        @Override
        public ViewData[] newArray(int size) {
            return new ViewData[size];
        }
    };
}
