package com.example.hck3rz.hck3rz;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.os.Vibrator;
import android.widget.ImageButton;
import android.widget.TextView;

import User.Game;
import User.PostMan;

import android.content.Intent;

public class DesktopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Game.currentAppContext=this;
        requestWindowFeature(Window.FEATURE_NO_TITLE); //Removes the ugly title for the app.
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN); //Makes the activity app fullscreen.

        setContentView(R.layout.activity_desktop);

        ImageButton b= findViewById(R.id.terminalButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                terminal_click(v);
            }
        });

        ImageButton email=findViewById(R.id.mailButton);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mail_click(v);
            }
        });

        try {
            PostMan.ensureAllEmailsAreUnique();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    //Credit for vibration goes here: https://stackoverflow.com/questions/13950338/how-to-make-an-android-device-vibrate
    public void goodVibrations(View v){

        Utilities.SoundUtilities.playSound(this,R.raw.computer_error);

        // Get instance of Vibrator from current Context
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // Vibrate for 400 milliseconds
        vibrator.vibrate(400);
    }


    public void terminal_click(View v){
        Intent intent;
        intent = new Intent(".AxeGameActivity");
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    /**
     * Starts up the mail inbox activity.
     * @param v
     */
    public void mail_click(View v){
        Intent intent=new Intent(this,EmailInboxActivity.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }
}
