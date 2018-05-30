package com.example.hck3rz.hck3rz;

import android.annotation.SuppressLint;
import android.content.Intent;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.os.Vibrator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import User.Game;
import User.PostMan;

import android.content.Intent;

import com.example.hck3rz.hck3rz.FolderActivities.FolderActivityMemes;

public class DesktopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Game.activity=this;
        Game.SetFullScreen();

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

        //Make the image view a button instead.
        ImageView simonSaysButton=findViewById(R.id.DESKTOP_ACTIVITY_IMAGE_BUTTON_SIMON_SAYS);
        simonSaysButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simonSays(v);
            }
        });
        newMailvisible();

    }

    public void newMailvisible(){
        ImageView newMailIcon=findViewById(R.id.DESKTOP_ACTIVITY_IMAGE_VIEW_NEW_MAIL_ICON);
        ImageButton mailButton=findViewById(R.id.mailButton);
        if(PostMan.hasNewMail==false){
            newMailIcon.setVisibility(View.INVISIBLE);
            mailButton.setImageResource(R.drawable.email);
            PostMan.playNewMailSound();
        }
        else{
            newMailIcon.setVisibility(View.VISIBLE);
            mailButton.setImageResource(R.drawable.unreadmail);
            PostMan.playNewMailSound();
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        newMailvisible();
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

    public void optionsClick(View v){
        Intent i = new Intent(this,OptionsActivity.class);
        startActivity(i);
    }

    public void memesFolderClick(View v){
        Intent i = new Intent(this, FolderActivityMemes.class);
        startActivity(i);
    }

    public void simonSays(View v){
        Intent i = new Intent(this,MinigameSimonSaysActivity.class);
        startActivity(i);
    }
}
