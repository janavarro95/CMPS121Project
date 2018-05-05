package com.example.hck3rz.hck3rz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
<<<<<<< HEAD
import android.widget.TextView;

import User.Game;
import User.Player;
import Utilities.ColorUtilities;

public class MainActivity extends AppCompatActivity {

    private MainActivity instance;
=======
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static String user_name;
    public static String password;

>>>>>>> b442546e520959a53d7fbbc828e29e5c418eda6a
    private static Button okButton;
    private static TextView errorMessage;
    private Player dummyPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OnClickButtonListener();
        instance=this;

        dummyPlayer=Player.loadPlayerObject(MainActivity.this);

        //Attempt to load in info of last logged in player.
        if(dummyPlayer!=null){
            TextView user = findViewById(R.id.userNameEditText);
            TextView pass = findViewById(R.id.passwordEditText);


            user.setText(dummyPlayer.username.toString()); //Set the username.
            pass.setText(dummyPlayer.password); //Set the password.
        }

        //Load in save data to text fields here if you can find it!!!
    }
    public void OnClickButtonListener(){

        okButton = (Button) findViewById(R.id.okButton);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
<<<<<<< HEAD
                TextView user = findViewById(R.id.userNameEditText);
                TextView pass = findViewById(R.id.passwordEditText);

                CharSequence sequence=user.getText(); //Get the text from the username field.
                String username =  sequence.toString(); //Get the string from the char sequence.

                CharSequence sequence1 = pass.getText();
                String password = sequence1.toString();

                if(username.length()==0){
                    errorMessage= findViewById(R.id.loginErrorTextView);
                    ColorUtilities.setTextColor(errorMessage, instance,R.color.errorRedMessage);
                    errorMessage.setText("Error: Username can't be length 0");
                    return;
                }
                if(password.length()==0){
                    errorMessage= findViewById(R.id.loginErrorTextView);
                    ColorUtilities.setTextColor(errorMessage,instance,R.color.errorRedMessage);
                    errorMessage.setText("Error: Password can't be length 0");
                    return;
                }

                //Attempt to load the player from a file and if we can't we create a new one.
                try {
                    Player.loadToGame(MainActivity.this);
                }
                catch (Exception err) {
                    try {
                        if (Game.player == null) {
                            Game.player = new Player(username, password);
                            //Game.player.save(MainActivity.this);
                            Game.player.save(MainActivity.this); //Attempt to save and just go back and forth.
                        }

                    }
                    catch (Exception err2){
                        Log.v("BAD LOAD",err2.toString());
                    }
                }

                if(Game.player==null){
                    Game.player=new Player(username,password);
                    Game.player.save(MainActivity.this);
                }

                if(username!=Game.player.username){
                    Game.player.username=username;
                    Game.player.save(MainActivity.this);
                }
=======
                // store the username and password in static strings
                // so that you can access them from other activities
                EditText name = findViewById(R.id.userNameEditText);
                user_name = name.getText().toString();

                EditText psw = findViewById(R.id.passwordEditText);
                password = psw.getText().toString();

>>>>>>> b442546e520959a53d7fbbc828e29e5c418eda6a
                Intent intent = new Intent(".DesktopActivity");
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
    }

}

