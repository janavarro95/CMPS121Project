package com.example.owner.voicememos;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

//Resource used: https://developer.android.com/guide/webapps/webview
public class WebActivity extends AppCompatActivity {

    WebView myWebView;
    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        //Create a web view
        myWebView = (WebView) findViewById(R.id.webview);
        myWebView.loadUrl("https://users.soe.ucsc.edu/~dustinadams/CMPS121/assignment3/www/index.html");

        //Change my settings to enable javascript.
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //Link my android object to be my WebAppInterface.
        myWebView.addJavascriptInterface(new WebAppInterface(this), "Android");
    }
}
