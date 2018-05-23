package com.example.hck3rz.hck3rz;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.TextView;

import User.Game;
import User.OptionsPackage.Options;
import User.OptionsPackage.SoundOptions;
import User.Player;
import User.PostMan;
import Utilities.ColorUtilities;
import android.widget.EditText;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {



    private static Button okButton;
    private static TextView errorMessage;

    public static MainActivity instance;
    private Player dummyPlayer;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Game.activity=this;
            Game.SetFullScreen();
            setContentView(R.layout.activity_main);;
            instance = this;
            PostMan.initializeEmails(this);


            Game.options=new Options(new SoundOptions(1.00f,false)); //Create some new game options.

            dummyPlayer = Player.loadPlayerObject(MainActivity.this);

            //Attempt to load in info of last logged in player.
            if (dummyPlayer != null) {
                TextView user = findViewById(R.id.userNameEditText);
                TextView pass = findViewById(R.id.passwordEditText);


                user.setText(dummyPlayer.username.toString()); //Set the username.
                pass.setText(dummyPlayer.password); //Set the password.
            }

            OnClickButtonListener();
            }




    public void loginSound(View v){

        Utilities.SoundUtilities.playSound(this,R.raw.microsoft_windows_xp_startup_sound);

            //Load in save data to text fields here if you can find it!!!
        }


        public void OnClickButtonListener() {

            okButton = (Button) findViewById(R.id.okButton);
            okButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    TextView user = findViewById(R.id.userNameEditText);
                    TextView pass = findViewById(R.id.passwordEditText);

                    CharSequence sequence = user.getText(); //Get the text from the username field.
                    String username = sequence.toString(); //Get the string from the char sequence.

                    CharSequence sequence1 = pass.getText();
                    String password = sequence1.toString();

                    if (username.length() == 0) {
                        errorMessage = findViewById(R.id.loginErrorTextView);
                        ColorUtilities.setTextColor(errorMessage, instance, R.color.errorRedMessage);
                        errorMessage.setText("Error: Username can't be length 0");
                        return;
                    }
                    if (password.length() == 0) {
                        errorMessage = findViewById(R.id.loginErrorTextView);
                        ColorUtilities.setTextColor(errorMessage, instance, R.color.errorRedMessage);
                        errorMessage.setText("Error: Password can't be length 0");
                        return;
                    }

                    //Attempt to load the player from a file and if we can't we create a new one.
                    try {
                        Player.loadToGame(MainActivity.this);
                    } catch (Exception err) {
                        try {
                            if (Game.player == null) {
                                Game.player = new Player(username, password);
                                //Game.player.save(MainActivity.this);
                                Game.player.save(MainActivity.this); //Attempt to save and just go back and forth.
                            }

                        } catch (Exception err2) {
                            Log.v("BAD LOAD", err2.toString());
                        }
                    }

                    if (Game.player == null) {
                        Game.player = new Player(username, password);
                        try {
                            Game.player.save(MainActivity.this);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if (username != Game.player.username) {
                        Game.player.username = username;
                        try {
                            Game.player.save(MainActivity.this);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    loginSound(view);

                    Intent intent = new Intent(".DesktopActivity");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);

                }
            });
        }
    }
