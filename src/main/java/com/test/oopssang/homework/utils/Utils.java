package com.test.oopssang.homework.utils;

import android.support.annotation.NonNull;

import com.test.oopssang.homework.data.instargramdata.Item;
import com.test.oopssang.homework.data.viewdata.ViewData;

import java.util.ArrayList;

/**
 * Created by sang on 2017-04-19.
 */

public class Utils {
    /**
     * 서버응답값을 받아 화면에서 필요한 url / Width / Height 정보만을 가지고 있는 ViewData List로 리턴
     * @param t ArrayList<Item>
     * @return ArrayList<ViewData>
     */
    public static ArrayList<ViewData> changeData(@NonNull ArrayList<Item> t) {
        ArrayList<ViewData> temp = new ArrayList<ViewData>();
        for(Item item : t){
            ViewData data = new ViewData();
            data.setImageUrl(item.getImages().getStandardResolution().getUrl());
            data.setHeigth(item.getImages().getStandardResolution().getWidth());
            data.setWidth(item.getImages().getStandardResolution().getHeight());
            data.setId(item.getId());
            temp.add(data);
        }
        return temp;
    }

    /**
     * 서버응답값을 받아 화면에서 필요한 url / Width / Height 정보를 전달받은 ViewData List에 Add
     * @param t ArrayList<Item>
     * @return ArrayList<ViewData>
     */
    public static void addData(@NonNull ArrayList<ViewData> list, @NonNull ArrayList<Item> t) {
        for(Item item : t){
            ViewData data = new ViewData();
            data.setImageUrl(item.getImages().getStandardResolution().getUrl());
            data.setHeigth(item.getImages().getStandardResolution().getWidth());
            data.setWidth(item.getImages().getStandardResolution().getHeight());
            data.setId(item.getId());
            list.add(data);
        }
    }

}
