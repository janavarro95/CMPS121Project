package com.example.hck3rz.hck3rz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import User.Game;
import User.IFunction;
import User.PostMan;
import User.TimerWrapper;
import Utilities.SoundUtilities;

public class BootUpActivity extends AppCompatActivity {

    TimerWrapper timer;

    ProgressBar progressBar;

    //Custom font for the logo made at https://fontmeme.com/pixel-fonts/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Game.activity=this;
        Game.SetFullScreen();
        setContentView(R.layout.activity_boot_up);
        PostMan.hasNewMail=false;

        progressBar=findViewById(R.id.BOOTUP_ACTIVITY_PROGRESS_BAR_LOADING_BAR);

        timer=new TimerWrapper("Timer", 4, new IFunction() {
            @Override
            public void execute() {
                logInScreen();
            }

            @Override
            public void countDownExecute() {
                progressBar.incrementProgressBy(1);
            }

        }, false, false, 1000);
        SoundUtilities.playSong(this);
    }



    public void logInScreen(){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

}
