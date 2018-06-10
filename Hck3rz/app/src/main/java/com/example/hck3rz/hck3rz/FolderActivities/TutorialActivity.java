package com.example.hck3rz.hck3rz.FolderActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.hck3rz.hck3rz.R;

import User.DialogueText;
import User.Game;
import User.PostMan;
import Utilities.ColorUtilities;

public class TutorialActivity extends AppCompatActivity {

    TextView introText;

    DialogueText text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        Game.activity=this;
        introText=findViewById(R.id.TUTORIAL_ACTIVITY_TEXT_VIEW_INTRO_TEXT);
        ColorUtilities.setTextColor(introText,Game.activity,R.color.green);
        String content= PostMan.readFromFile(this,"TutorialTextChallenge.txt");
        text=new DialogueText(introText,content,DialogueText.SPEED_FAST);
        text.startDialogue();
    }


    public void startFirewall(View v){
        Intent i=new Intent(Game.activity,FirewallBreakActivity.class);
        startActivity(i);
    }

}
