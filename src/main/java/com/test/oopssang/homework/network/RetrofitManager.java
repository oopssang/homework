package com.test.oopssang.homework.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sang on 2017-04-04.
 */

public class RetrofitManager{
    private final static String BASE_URL_INSTAGRAM = "https://www.instagram.com/";

    public static InstargramService getInstargramService(){
        StringBuilder builder = new StringBuilder();
        builder.append(BASE_URL_INSTAGRAM);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(builder.toString())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InstargramService service = retrofit.create(InstargramService.class);
        return service;
    }
}
