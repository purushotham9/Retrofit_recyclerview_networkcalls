package com.example.kvanamacair4.calenderevents.singleton;

import android.util.Log;

import com.example.kvanamacair4.calenderevents.util.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SessionManager {
    private static final String TAG = SessionManager.class.getSimpleName();

    private static final SessionManager ourInstance = new SessionManager();

    public static SessionManager getInstance() {
        return ourInstance;
    }

    private SessionManager() {
    }

    public void storeSession(String loginInfo) {
        try {
            JSONObject jsonObject = new JSONObject(loginInfo);
            SharedPreferenceManager.getInstance().putString(Constants.ACCESS_TOKEN, jsonObject.getString("access_token"));
            SharedPreferenceManager.getInstance().putString(Constants.TOKEN_TYPE, jsonObject.getString("token_type"));
            SharedPreferenceManager.getInstance().putString(Constants.USERNAME, jsonObject.getString("userName"));
            SharedPreferenceManager.getInstance().putString(Constants.USERFULLNAME, jsonObject.getString("userFullName"));
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "Store Session In Sp: " + e.getLocalizedMessage());
        }
    }

    public void storeHolidaysSession(String loginInfo) {
        try {
            JSONArray jsonArray = new JSONArray(loginInfo);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                SharedPreferenceManager.getInstance().putString(Constants.HOLIDAYEVENTTYPE, jsonObject.getString("holidayEventType"));
                SharedPreferenceManager.getInstance().putString(Constants.TITLE, jsonObject.getString("title"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getAccessToken() {
        return SharedPreferenceManager.getInstance().getString(Constants.ACCESS_TOKEN, "");
    }

    public String getTokenType() {
        return SharedPreferenceManager.getInstance().getString(Constants.TOKEN_TYPE, "");
    }

    public String getTitle() {
        return SharedPreferenceManager.getInstance().getString(Constants.TITLE, "");
    }
}
