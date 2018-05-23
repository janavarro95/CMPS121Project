package com.example.hck3rz.hck3rz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

public class MinigameSimonSaysActivity extends AppCompatActivity {


    ImageButton downButton;
    ImageButton upButton;
    ImageButton leftButton;
    ImageButton rightButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minigame_simon_says);

        downButton = findViewById(R.id.MINIGAME_ACTIVITY_SIMON_SAYS_IMAGE_BUTTON_DOWN);
        upButton = findViewById(R.id.MINIGAME_ACTIVITY_SIMON_SAYS_IMAGE_BUTTON_UP);
        leftButton = findViewById(R.id.MINIGAME_ACTIVITY_SIMON_SAYS_IMAGE_BUTTON_LEFT);
        rightButton = findViewById(R.id.MINIGAME_ACTIVITY_SIMON_SAYS_IMAGE_BUTTON_RIGHT);


        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        downButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP ||
                        event.getAction() == MotionEvent.ACTION_CANCEL) {
                    //increaseSize();
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //resetSize();
                    return true;
                }
                return false;
            }
        });


    }
}
