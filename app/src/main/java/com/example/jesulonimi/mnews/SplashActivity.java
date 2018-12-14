package com.example.jesulonimi.mnews;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;
import maes.tech.intentanim.CustomIntent;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EasySplashScreen easySplashScreenView = new EasySplashScreen(SplashActivity.this)
                .withFullScreen()
                .withTargetActivity(MainActivity.class)
                .withSplashTimeOut(2000)
                .withBackgroundResource(R.drawable.spi)
                .withFooterText("produced by Jesulonimi")
                ;


        easySplashScreenView.getFooterTextView().setTextColor(Color.GREEN);
        View v=easySplashScreenView.create();
        setContentView(v);
    }
    @Override
    public void finish() {
        super.finish();
        CustomIntent.customType(this,"left-to-right");
    }

}
