package com.example.hck3rz.hck3rz;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import User.Player;
import Utilities.ColorUtilities;

public class MainActivity extends AppCompatActivity {

    private MainActivity instance;
    private static Button okButton;
    private static TextView errorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OnClickButtonListener();
        instance=this;


    }
    public void OnClickButtonListener(){

        okButton = (Button) findViewById(R.id.okButton);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

                Player.Username=username;
                Player.Password=password;

                Intent intent = new Intent(".DesktopActivity");
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);

                loginSound(view);
            }
        });
    }
    public void loginSound(View v){

        Utilities.SoundUtilities.playSound(this,R.raw.microsoft_windows_xp_startup_sound);

    }

}

