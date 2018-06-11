package com.example.hck3rz.hck3rz.FolderActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.hck3rz.hck3rz.MatrixDigitalRainActivity;
import com.example.hck3rz.hck3rz.R;

import User.DialogueText;
import User.Game;
import User.PostMan;
import Utilities.ColorUtilities;

public class MatrixDigitalRainTutorialActivity extends AppCompatActivity {


    TextView introText;

    DialogueText text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix_digital_rain_tutorial);
        introText=findViewById(R.id.TUTORIAL_ACTIVITY_TEXT_VIEW_INTRO_TEXT);
        ColorUtilities.setTextColor(introText,this,R.color.green);
        String content= PostMan.readFromFile(this,"MatrixDigitalRainTextChallenge");
        text=new DialogueText(introText,content,DialogueText.SPEED_FAST);
        text.startDialogue();
    }

    public void startMatrixDigitalRain(View v){
        Intent i=new Intent(".MatrixDigitalRainActivity");
        startActivity(i);
    }
}
