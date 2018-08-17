package com.example.kvanamacair4.calenderevents.network;

import com.example.kvanamacair4.calenderevents.util.StringConverterFactory;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHandler {
    public static final String BASE_URL = "http://13.77.176.215";

    private static final RetrofitHandler ourInstance = new RetrofitHandler();

    public static RetrofitHandler getInstance() {
        return ourInstance;
    }

    private RetrofitHandler() {
    }

    private Retrofit hertzRetrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(StringConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private TrackerApi edunixApikeys = hertzRetrofit.create(TrackerApi.class);

    public Call<String> calendar(String authorization, String domain_name) {
        return edunixApikeys.getCalender(authorization, domain_name);
    }

    public Call<String> login(String titles,
                                 String grant_type,
                                 String username,
                                 String password,
                                 String domain_name) {
        return edunixApikeys.login(titles, grant_type, username, password, domain_name);
    }

    public Call<String> getImage(String authorization, String domain_name) {
        return edunixApikeys.getImage(authorization, domain_name);
    }
}
