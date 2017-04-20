package com.test.oopssang.homework.network;

import com.test.oopssang.homework.data.instargramdata.Items;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by sang on 2017-04-04.
 */

public interface InstargramService {
    @GET("{user_id}/media/")
    Call<Items> getdata(@Path("user_id") String user_id);

    /**
     * Instargram media API 연동
     * @param user_id  Instargram userID
     * @param max_id 게시물 ID   default "0" or null
     * @return
     */
    @GET("{user_id}/media/")
    Call<Items> getdata(@Path("user_id") String user_id, @Query("max_id") String max_id);
}
