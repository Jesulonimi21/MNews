package com.example.jesulonimi.mnews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class AdditionalInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional_info);
        WebView webView=(WebView) findViewById(R.id.mWebView);
        webView.loadUrl("file:///android_asset/about.html");
    }
}
