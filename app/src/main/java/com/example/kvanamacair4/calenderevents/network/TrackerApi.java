package com.example.kvanamacair4.calenderevents.network;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface TrackerApi {

    @GET("api/HolidayEvent")
    Call<String> getCalender(@Header("Authorization") String authorization, @Header("domain-name") String domain_name);

    //Header, Boby = Field, Query, => params
    //GET, POST, PUT, DELETE       => calls
    //READ, CREATE, UPDATE, DELETE

    @FormUrlEncoded
    @POST("edunix/TOKEN")
    Call<String> login(@Field("Content-Type") String titles,
                       @Field("grant_type") String grant_type,
                       @Field("username") String username,
                       @Field("password") String password,
                       @Field("domain-name") String domain_name);
}


