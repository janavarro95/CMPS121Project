package com.example.hck3rz.hck3rz;

<<<<<<< HEAD
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
import User.Player;
=======
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
>>>>>>> b442546e520959a53d7fbbc828e29e5c418eda6a

public class DesktopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //Removes the ugly title for the app.
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN); //Makes the activity app fullscreen.

        setContentView(R.layout.activity_desktop);

<<<<<<< HEAD


        ImageButton b= findViewById(R.id.terminalButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodVibrations(v);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            TextView display = findViewById(R.id.displayGarbage);
           display.setText(Game.player.username);
        }
        catch (Exception err){
            Log.v("Setting display error",err.toString());
        }
    }

    //Credit for vibration goes here: https://stackoverflow.com/questions/13950338/how-to-make-an-android-device-vibrate
    public void goodVibrations(View v){

        Utilities.SoundUtilities.playSound(this,R.raw.computer_error);

        // Get instance of Vibrator from current Context
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // Vibrate for 400 milliseconds
        vibrator.vibrate(400);
=======
    }
    public void terminal_click(View v){
        Intent intent;
        intent = new Intent(".TerminalActivity");
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
>>>>>>> b442546e520959a53d7fbbc828e29e5c418eda6a
    }
}
