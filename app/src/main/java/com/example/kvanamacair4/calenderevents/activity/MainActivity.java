package com.example.kvanamacair4.calenderevents.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kvanamacair4.calenderevents.R;
import com.example.kvanamacair4.calenderevents.network.RetrofitHandler;
import com.example.kvanamacair4.calenderevents.singleton.SessionManager;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private Button mbtn, mlogin;
    private EditText muserName, mpassword, mdomainName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mbtn = findViewById(R.id.btn);
        muserName = findViewById(R.id.et_user_name);
        muserName.setText("moiz.nixfi@gmail.com");
        mpassword = findViewById(R.id.et_password);
        mpassword.setText("Password@1");
        mdomainName = findViewById(R.id.et_domain_name);
        mdomainName.setText("School.Database");
        mlogin = findViewById(R.id.btn_login);
        mbtn.setOnClickListener(this);
        mlogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                String username= muserName.getText().toString();
                String password= mpassword.getText().toString();
                String domainname= mdomainName.getText().toString();

                RetrofitHandler.getInstance().login("application/x-www-form-urlencoded", "password", username, password, domainname).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            try {
                                JSONObject jsonObject = new JSONObject(response.body());
                                SessionManager.getInstance().storeSession(response.body());
                                String  username = jsonObject.getString("userName");
                                Toast.makeText(getApplicationContext(), username, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), BranchActivity.class);
                                startActivity(intent);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d(TAG, "onFailure:  " + t.getLocalizedMessage());
                    }
                });
                break;
            case R.id.btn:
                Intent intent = new Intent(this, BranchActivity.class);
                startActivity(intent);
                break;
        }
    }
}
