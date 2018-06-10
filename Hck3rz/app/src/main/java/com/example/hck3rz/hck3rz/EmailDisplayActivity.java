package com.example.hck3rz.hck3rz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import User.DialogueText;
import User.Email;
import User.Game;
import User.PostMan;

public class EmailDisplayActivity extends AppCompatActivity {

    Button returnButton;
    Button MiniGameButton;
    TextView emailContents;
    TextView subject;
    TextView senderAddress;
    TextView senderName;

    ImageView senderIcon;

    DialogueText text;

    public static Email currentEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Game.activity=this;
        Game.SetFullScreen();
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

        text=new DialogueText(emailContents,currentEmail.contents,DialogueText.SPEED_FAST);
        text.startDialogue();
        //emailContents.setText(currentEmail.contents);

        subject=findViewById(R.id.TEXT_VIEW_EMAIL_DISPLAY_SUBJECT);
        subject.setText(currentEmail.subject);

        senderAddress=findViewById(R.id.TEXT_VIEW_EMAIL_DISPLAY_SENDER_ADDRESS);
        senderAddress.setText(currentEmail.senderEmail);

        senderName=findViewById(R.id.TEXT_VIEW_EMAIL_DISPLAY_SENDER_NAME);
        senderName.setText(currentEmail.senderName);

        senderIcon=findViewById(R.id.IMAGE_VIEW_EMAIL_DISPLAY_SENDER_ICON);
        senderIcon.setImageResource(currentEmail.senderIcon);

        MiniGameButton=findViewById(R.id.EMAIL_DISPLAY_ACTIVITY_BUTTON_ACCEPT_MINIGAME);

        if(currentEmail.minigameToGoTo!=null) {
            MiniGameButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Game.getCurrentAppContext(), currentEmail.minigameToGoTo.getClass());
                    startActivity(i);
                }
            });
        }
        else{
            MiniGameButton.setVisibility(View.INVISIBLE);
        }

        Game.player.readEmail(currentEmail);
        currentEmail.readEmail();
    }
}
