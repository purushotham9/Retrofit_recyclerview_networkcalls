package com.example.kvanamacair4.calenderevents.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.kvanamacair4.calenderevents.R;

import gr.net.maroulis.library.EasySplashScreen;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mbtn = findViewById(R.id.btn);
        mbtn.setOnClickListener(this);
        View easySplashScreenView = new EasySplashScreen(MainActivity.this)
                .withFullScreen()
                .withTargetActivity(BranchActivity.class)
                .withSplashTimeOut(4000)
                .withBackgroundResource(android.R.color.holo_red_light)
                .withHeaderText("Header")
                .withFooterText("Copyright 2016")
                .withBeforeLogoText("My cool company")
                .withLogo(R.drawable.ic_launcher_background)
                .withAfterLogoText("Some more details")
                .create();
        setContentView(easySplashScreenView);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, BranchActivity.class);
        startActivity(intent);
    }
}
