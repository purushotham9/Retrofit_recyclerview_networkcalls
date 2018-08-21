package com.example.kvanamacair4.calenderevents.singleton;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceManager {
    public static final String ME = "your_app_name";

    private SharedPreferences sharedPreferences;

    private static SharedPreferenceManager ourInstance = new SharedPreferenceManager();

    public static SharedPreferenceManager getInstance() {
        return ourInstance;
    }

    private SharedPreferenceManager() {
    }

    public void init(Context context) {
        sharedPreferences = context.getSharedPreferences(ME, Context.MODE_PRIVATE);
    }

    public void remove(String key) {
        (sharedPreferences.edit()).remove(key).apply();
    }

    public String getString(String key, String defaultVal) {
        return sharedPreferences.getString(key, defaultVal);
    }

    public void putString(String key, String value) {
        (sharedPreferences.edit()).putString(key, value).apply();
    }

    public void putLong(String key, long value) {
        (sharedPreferences.edit()).putLong(key, value).apply();
    }

    public boolean getBoolean(String key, boolean defaultVal) {
        if (sharedPreferences != null) {
            return sharedPreferences.getBoolean(key, defaultVal);
        } else {
            return defaultVal;
        }
    }
}
