package com.example.hck3rz.hck3rz.FolderActivities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.hck3rz.hck3rz.R;

import java.net.URI;

import User.Game;

public class YoutubeVideoActivity extends AppCompatActivity {

    public static String url;


    WebView myWebView;
    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_video);

        //Create a web view
        myWebView = (WebView) findViewById(R.id.webView);
        myWebView.loadUrl("https://www.youtube.com/watch?v=Oh3fQAPGyFk");

        //Change my settings to enable javascript.
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //Link my android object to be my WebAppInterface.
        //myWebView.addJavascriptInterface(new WebAppInterface(this), "Android");
    }

    @Override
    public void onResume(){
        super.onResume();
        finish(); //When I'm done watching the video go back to the meme page.
    }




    public static Intent startVideo(String url){
       YoutubeVideoActivity.url=url;
        Intent i=new Intent(Game.activity,YoutubeVideoActivity.class);
        return i;
    }
}
