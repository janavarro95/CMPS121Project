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
import User.IFunction;
import User.PostMan;
import User.TimerWrapper;
import Utilities.TimeUtilities;

import android.content.Intent;

import com.example.hck3rz.hck3rz.FolderActivities.FolderActivityMemes;

public class DesktopActivity extends AppCompatActivity {

    TimerWrapper timer;
    TextView time;

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

        PostMan.addEmailToInbox(PostMan.getEmailByUniqueID("FirstEmail"),false);
        PostMan.addEmailToInbox(PostMan.getEmailByUniqueID("SimonSays1"),false);
        PostMan.addEmailToInbox(PostMan.getEmailByUniqueID("Tree1"),false);
        PostMan.addEmailToInbox(PostMan.getEmailByUniqueID("Matrix1"),false);
        PostMan.addEmailToInbox(PostMan.getEmailByUniqueID("PrincessKidnapped"),false);
        PostMan.playNewMailSound();

        newMailvisible();
        time=findViewById(R.id.DESKTOP_ACTIVITY_TEXTVIEW_TIME);
        timer=new TimerWrapper("System.Time", 1, new IFunction() {
            @Override
            public void execute() {

            }

            @Override
            public void countDownExecute() {
                time.setText(TimeUtilities.createTime());
            }
        },true,false,1000);

    }

    public void newMailvisible(){
        ImageView newMailIcon=findViewById(R.id.DESKTOP_ACTIVITY_IMAGE_VIEW_NEW_MAIL_ICON);
        ImageButton mailButton=findViewById(R.id.mailButton);

        boolean hasReadAllMail=true;
        for(int i=0;i<Game.player.emails.size();i++){
            if(Game.player.emails.get(i).hasBeenRead==false){
                hasReadAllMail=false;
                break;
            }
        }
        PostMan.hasNewMail=(!hasReadAllMail);

        if(!PostMan.hasNewMail){
            newMailIcon.setVisibility(View.INVISIBLE);
            mailButton.setImageResource(R.drawable.email);
            //PostMan.playNewMailSound();
        }
        else{
            newMailIcon.setVisibility(View.VISIBLE);
            mailButton.setImageResource(R.drawable.unreadmail);
            //PostMan.playNewMailSound();
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
        intent = new Intent(this, MatrixDigitalRainActivity.class);
//      intent = new Intent(".AxeGameActivity");
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
