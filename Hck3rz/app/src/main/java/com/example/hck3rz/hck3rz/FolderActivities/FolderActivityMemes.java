package com.example.hck3rz.hck3rz.FolderActivities;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.hck3rz.hck3rz.R;

import User.Game;

public class FolderActivityMemes extends AppCompatActivity {

    ImageView rickRoll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Game.activity=this;
        Game.SetFullScreen();
        setContentView(R.layout.activity_folder_memes);

        rickRoll=findViewById(R.id.MEMES_IMAGE_RICK_ROLL);
        rickRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RickRollMeme(v);
            }
        });
    }

    public void close(View v){
        finish();
    }


    public void RickRollMeme(View v){
        startActivity(YoutubeVideoActivity.startVideo("https://www.youtube.com/watch?v=Oh3fQAPGyFk&app=mobile"));
    }
}
