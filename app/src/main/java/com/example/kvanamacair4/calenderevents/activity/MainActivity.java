package com.example.kvanamacair4.calenderevents.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kvanamacair4.calenderevents.R;
import com.example.kvanamacair4.calenderevents.network.RetrofitHandler;
import com.example.kvanamacair4.calenderevents.network.TrackerApi;
import com.example.kvanamacair4.calenderevents.singleton.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

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
                String username = muserName.getText().toString();
                String password = mpassword.getText().toString();
                String domainname = mdomainName.getText().toString();
                RetrofitHandler.getInstance().login("application/x-www-form-urlencoded", "password", username, password, domainname).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            try {
                                JSONObject jsonObject = new JSONObject(response.body());
                                SessionManager.getInstance().storeSession(response.body());
                                String username = jsonObject.getString("userName");
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
                new GetLoginDetails(getApplicationContext()).execute();
                break;
            default:
                break;
        }
    }

    public class GetLoginDetails extends AsyncTask<String, Void, String> {
        private static final String TAG = "BlockAniAsync";
        private final Context mContext;
        private int responseCode;
        String displayMessage = null;

        public GetLoginDetails(Context mContext) {

            this.mContext = mContext;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            String response = useHttpurl(strings);

            return response;
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            Log.e(TAG, "response ======== " + response);
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    SessionManager.getInstance().storeSession(response);
                    String username = jsonObject.getString("userName");
                    Toast.makeText(getApplicationContext(), username, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), BranchActivity.class);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        private String useHttpurl(String[] inputs) {
            String responseString = "";
            try {
                // Get the contacts information
                URL url = new URL("http://13.77.176.215/TOKEN");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(10000);
                connection.setConnectTimeout(60000);
                connection.setRequestMethod("GET");
                connection.setUseCaches(false);
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.connect();

                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                writer.write("");
                String username = muserName.getText().toString();
                String password = mpassword.getText().toString();
                String domainname = mdomainName.getText().toString();
                // Send require parameters to the Service call .
                writer.write(getGetDataString(getParamsInHashMap("application/x-www-form-urlencoded ", "password", domainname, username, password)));
                writer.flush();
                writer.close();
                outputStream.close();
                responseCode = connection.getResponseCode();
                Log.e(TAG, "responseCode ======= " + responseCode);
                InputStream inputStream = connection.getInputStream();

                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    String line;
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    while ((line = bufferedReader.readLine()) != null) {
                        responseString += line;
                    }
                } else {
                    responseString = "";
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return responseString;

        }

        private String getGetDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
            StringBuilder result = new StringBuilder();
            boolean first = true;
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (first)
                    first = false;
                else
                    result.append("&");

                result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            }

            return result.toString();
        }

        private HashMap<String, String> getParamsInHashMap(String titles, String grant_type, String domain_name, String username, String password) {
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("Content-Type", titles);
            params.put("grant_type", grant_type);
            params.put("username", username);
            params.put("password", password);
            params.put("domain-name", domain_name);
            Log.e(TAG, "Content-Type ===== " + titles);
            Log.e(TAG, "grant_type===== " + grant_type);
            Log.e(TAG, "username ===== " + username);
            Log.e(TAG, "password ===== " + password);
            Log.e(TAG, "domain_name ===== " + domain_name);

            return params;
        }

    }
}
