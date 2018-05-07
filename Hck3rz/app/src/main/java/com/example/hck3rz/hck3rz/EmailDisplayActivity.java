package com.example.hck3rz.hck3rz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import User.Email;
import User.PostMan;

public class EmailDisplayActivity extends AppCompatActivity {

    Button returnButton;
    TextView emailContents;

    public static Email currentEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_display);

        returnButton = findViewById(R.id.BUTTON_RETURN_TO_INBOX);
        emailContents =findViewById(R.id.TEXT_VIEW_EMAIL_CONTENTS);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentEmail=null;
                finish();
            }
        });

        emailContents.setText(PostMan.readFromFile(this,"texty.txt"));
    }
}
