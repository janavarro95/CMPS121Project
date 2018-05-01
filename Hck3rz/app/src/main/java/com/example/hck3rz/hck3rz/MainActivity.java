package com.example.hck3rz.hck3rz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
<<<<<<< HEAD
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static String user_name;
    public static String password;

=======
import android.widget.TextView;

import User.Player;
import Utilities.ColorUtilities;

public class MainActivity extends AppCompatActivity {

    private MainActivity instance;
>>>>>>> DesktopDev
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
<<<<<<< HEAD
        okButton = findViewById(R.id.okButton);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // store the username and password in static strings
                // so that you can access them from other activities
                EditText name = findViewById(R.id.userNameEditText);
                user_name = name.getText().toString();

                EditText psw = findViewById(R.id.passwordEditText);
                password = psw.getText().toString();
=======

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
>>>>>>> DesktopDev

                Intent intent = new Intent(".DesktopActivity");
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
    }

}

