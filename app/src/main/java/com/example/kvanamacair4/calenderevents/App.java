package com.example.kvanamacair4.calenderevents;

import android.app.Application;

import com.example.kvanamacair4.calenderevents.singleton.SharedPreferenceManager;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferenceManager.getInstance().init(this);
    }
}
