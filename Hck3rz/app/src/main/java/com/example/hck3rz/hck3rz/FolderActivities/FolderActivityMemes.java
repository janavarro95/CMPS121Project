package com.example.hck3rz.hck3rz.FolderActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.hck3rz.hck3rz.R;

import User.Game;

public class FolderActivityMemes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Game.activity=this;
        Game.SetFullScreen();
        setContentView(R.layout.activity_folder_memes);

    }

    public void close(View v){
        finish();
    }
}
