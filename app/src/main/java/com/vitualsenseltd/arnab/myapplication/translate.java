package com.vitualsenseltd.arnab.myapplication;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by arnab on 8/2/2017.
 */

public interface translate {
    @GET("/language/translate/v2")
    Call<authPOJO> performTranslate(@Query("key")String key,
                                    @Query("q") String q,
                                    @Query("source") String source,
                                    @Query("target") String target);


}
