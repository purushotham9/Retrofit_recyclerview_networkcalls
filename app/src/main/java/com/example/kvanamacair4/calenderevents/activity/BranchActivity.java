package com.example.kvanamacair4.calenderevents.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kvanamacair4.calenderevents.R;
import com.example.kvanamacair4.calenderevents.adapter.RetrofitAdapter;
import com.example.kvanamacair4.calenderevents.model.DataData;
import com.example.kvanamacair4.calenderevents.network.RetrofitHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BranchActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = BranchActivity.class.getSimpleName();
    private List<DataData> eventList = new ArrayList<>();
    private RecyclerView mrecycler_view;
    RetrofitAdapter retrofitAdapter;
    private Button mbtn_service_testing_purpose, btn;
    String domainName = "School.Database";
    String bearer = "Bearer xdlAiSYF0Rl1aD13ObuRXKVA5YHkJGql82RYMyQ6lnsQEvYVZLNEgKv9xq6CsZiz2ed_-N-eETtP3xOOYxr9BV-igFOzvVVIkhuzyF5jqfbFyq3GYmDNE0pRfS294tsoaOuUdeCsq0j9IGO2MAzK8WKLIVcvMgV4Hpb99ZJUlKrrF6cq6YESUP_rL5swlk8ehUSmybOwQ6hJ08S4l5G01pawpcWYhP0SiZqHUiCqeiSm0lzbiGXwnnEoYmO9lP6wvnM7kRnAhSQMO21WovTk6kpRB1CXHz22wTnAWfXPAY9efhRbr-jDalXyxotyCwKBYFiN8KcJIgG2-9onWneq_fkU_-y7Sm3g3dleX1eZ7czssBAtMQiOQ86nLEK2tzeR2njH3aKpjZoJc3Lok7sy0tb-1tDJGhys9_l0yMKXbN9zH5PCHFKfwP1yMGcASJwY9cxQ_w0ghif9_oM8wzRNOCmgSTMMhw4YI2f8xNdXf4DvyFVX70iDhT87x7uhnvI7FeeJSlba3LkfOzgMG7-GBSkTVvzF6JEmLcRStpTGF0AdYDnDGUqHpCv2BFVaCEMU";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch);
        mbtn_service_testing_purpose = findViewById(R.id.btn_service_testing_purpose);
        btn = findViewById(R.id.btn);
        mrecycler_view = findViewById(R.id.recycler_view);
        mbtn_service_testing_purpose.setText("Testing");
        mbtn_service_testing_purpose.setOnClickListener(this);
        btn.setOnClickListener(this);
        retrofitAdapter = new RetrofitAdapter(eventList);
        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(getApplicationContext());
        mrecycler_view.setLayoutManager(mlayoutManager);
        mrecycler_view.setItemAnimator(new DefaultItemAnimator());
        mrecycler_view.setAdapter(retrofitAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_service_testing_purpose:
                RetrofitHandler.getInstance().calendar(bearer, domainName).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            Log.d(TAG, "onResponse: " + response.body());
                            try {
                                JSONArray jsonArray = new JSONArray(response.body());
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String holidayEvent = jsonObject.getString("holidayEventType");
                                    String title = jsonObject.getString("title");
                                    JSONObject bJsonObject = jsonObject.getJSONObject("branch");
                                    String name = bJsonObject.getString("name");
                                    DataData dataData = new DataData(holidayEvent, title, name);
                                    eventList.add(dataData);
                                }
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
                retrofitAdapter.notifyDataSetChanged();
                break;
            case R.id.btn:
                RetrofitHandler.getInstance().login("", "", "", "" , "").enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d(TAG, "onFailure:  " + t.getLocalizedMessage());
                    }
                });
                break;
            default:
                Log.d(TAG, "onClick: " + "Nothing to do.....");
                break;
        }
    }
}

