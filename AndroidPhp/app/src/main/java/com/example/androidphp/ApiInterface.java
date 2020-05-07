package com.example.androidphp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("Fetch.php")
    Call<List<Responsclass>> getUsers();

    @FormUrlEncoded
    @POST("imageupload.php")
    Call<ImageRespons> uploadimage(@Field("title") String title,
                                         @Field("image") String image
                                         );
}
