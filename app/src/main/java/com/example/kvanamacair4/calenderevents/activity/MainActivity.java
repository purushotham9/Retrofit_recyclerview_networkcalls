package com.example.kvanamacair4.calenderevents.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.kvanamacair4.calenderevents.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mbtn = findViewById(R.id.btn);
        mbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, BranchActivity.class);
        startActivity(intent);
    }
}
