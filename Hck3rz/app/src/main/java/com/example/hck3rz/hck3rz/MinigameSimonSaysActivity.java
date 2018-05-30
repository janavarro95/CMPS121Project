package com.example.hck3rz.hck3rz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import User.Game;
import User.IFunction;
import User.TimerWrapper;
import Utilities.ColorUtilities;
import Utilities.SoundUtilities;

import static com.example.hck3rz.hck3rz.MinigameSimonSaysActivity.direction.down;
import static com.example.hck3rz.hck3rz.MinigameSimonSaysActivity.direction.left;
import static com.example.hck3rz.hck3rz.MinigameSimonSaysActivity.direction.right;
import static com.example.hck3rz.hck3rz.MinigameSimonSaysActivity.direction.up;


/*
TODO:
make a score section for this game with a high score feature.
*/

public class MinigameSimonSaysActivity extends AppCompatActivity {

    public enum direction{
        up,
        right,
        down,
        left
    }

    public static ArrayList<direction> randomDirectionList;
    ArrayList<direction> userInput;
    int currentAmountToPutIn;

    ImageButton downButton;
    ImageButton upButton;
    ImageButton leftButton;
    ImageButton rightButton;

    TimerWrapper timer;
    TimerWrapper countDownTimer;


    int currentIndex;
    int maxTime;

    public int score;

    AppCompatActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minigame_simon_says);

        Game.activity=this;
        maxTime=10;
        score=0;
        downButton = findViewById(R.id.MINIGAME_ACTIVITY_SIMON_SAYS_IMAGE_BUTTON_DOWN);
        upButton = findViewById(R.id.MINIGAME_ACTIVITY_SIMON_SAYS_IMAGE_BUTTON_UP);
        leftButton = findViewById(R.id.MINIGAME_ACTIVITY_SIMON_SAYS_IMAGE_BUTTON_LEFT);
        rightButton = findViewById(R.id.MINIGAME_ACTIVITY_SIMON_SAYS_IMAGE_BUTTON_RIGHT);

        currentAmountToPutIn=3;
        this.randomDirectionList=new ArrayList<>();
        this.userInput=new ArrayList<>();
        initializeNewRound();

        activity=this;
    }

    public void initializeNewRound(){
        this.randomDirectionList.clear();
        disableUserInput();
        generateRandomDirection();
        currentIndex=0;
        timer=new TimerWrapper("SimonSaysTimer", 1*(currentAmountToPutIn+1), new IFunction() {
            @Override
            public void execute() {
                resetAllButtons();
                enableUserInput();
                currentIndex=0;

                countDownTimer=new TimerWrapper("CountdownTimer", maxTime, null,false,false,1000);
                countDownTimer.function=new IFunction() {
                    @Override
                    public void execute() {
                        gameOver();
                    }

                    @Override
                    public void countDownExecute() {
                        TextView texty=findViewById(R.id.MINIGAME_ACTIVITY_SIMON_SAYS_TEXT_VIEW_TIME_REMAINING);
                        String time="";
                        for(int i=0; i<countDownTimer.timeRemaining;i++){
                            time+="*";
                        }
                        texty.setText(time);
                    }
                };
            }

            @Override
            public void countDownExecute() {
                resetAllButtons();
                try {
                    if (randomDirectionList.get(currentIndex) == direction.down) {
                        downButtonClick();
                    }
                    if (randomDirectionList.get(currentIndex) == direction.up) {
                        upButtonClick();
                    }
                    if (randomDirectionList.get(currentIndex) == direction.left) {
                        leftButtonClick();
                    }
                    if (randomDirectionList.get(currentIndex) == direction.right) {
                        rightButtonClick();
                    }
                    currentIndex++;
                }
                catch (Exception err){

                }
            }

        },false,false,1000);


    }


    public void generateRandomDirection(){
        for(int i=0; i<currentAmountToPutIn; i++) {
            int randomDirection = Utilities.Math.Random.getRandomExclusive(0, 4);
            Log.v("Value",Integer.toString(randomDirection));
            if(randomDirection==0) {
                randomDirectionList.add(up);
            }
            else if(randomDirection==1){
                randomDirectionList.add(right);
            }
            else if(randomDirection==2){
                randomDirectionList.add(down);
            }
            else if(randomDirection==3){
                randomDirectionList.add(left);
            }
        }
        return; //Finish generting random directions.
    }

    public void clearRandomDirections(){
        this.randomDirectionList.clear();
    }

    //called when user has put in all of the input successfully.
    public void newRound(){
        clearRandomDirections();
        this.currentAmountToPutIn++;
        initializeNewRound();
        //reset timer;
    }

    public boolean validateInput(direction dir){
        if(dir==randomDirectionList.get(currentIndex)){
            currentIndex++;
            return true;
        }
        else{
            SoundUtilities.playSound(Game.activity,R.raw.buttonbooperror);
            if(dir==direction.down) badDownInput();
            if(dir==direction.up) badUpInput();
            if(dir==direction.left) badLeftInput();
            if(dir==direction.right) badRightInput();
            disableUserInput();
            countDownTimer.stop();
            timer=new TimerWrapper("BadTimer", 1, new IFunction() {
                @Override
                public void execute() {
                    finish();
                }

                @Override
                public void countDownExecute() {

                }
            },false,false,2000);
            return false;
        }
    }

    public void enableUserInput(){
        //down button
        downButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(validateInput(direction.down)) {
                        downButtonClick();
                        incrementScore();
                        isRoundFinished();
                    }
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP||
                        event.getAction() == MotionEvent.ACTION_CANCEL) {
                    downButton.setImageResource(R.drawable.downarrowdark);
                    return true;
                }
                return false;
            }
        });

        //up button
        upButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(validateInput(direction.up)) {
                        upButtonClick();
                        incrementScore();
                        isRoundFinished();
                    }
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP||
                        event.getAction() == MotionEvent.ACTION_CANCEL) {
                    upButton.setImageResource(R.drawable.uparrowdark);
                    return true;
                }
                return false;
            }
        });

        //left button
        leftButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(validateInput(direction.left)) {
                        leftButtonClick();
                        incrementScore();
                        isRoundFinished();
                    }
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP||
                        event.getAction() == MotionEvent.ACTION_CANCEL) {
                    leftButton.setImageResource(R.drawable.leftarrowdark);
                    return true;
                }
                return false;
            }
        });


        //right button
        rightButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(validateInput(direction.right)) {
                        rightButtonClick();
                        incrementScore();
                        isRoundFinished();
                    }
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP||
                        event.getAction() == MotionEvent.ACTION_CANCEL) {
                    rightButton.setImageResource(R.drawable.rightarrowdark);
                    return true;
                }
                return false;
            }
        });
    }

    public void disableUserInput(){
        downButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        upButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        leftButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        rightButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
    }


    public void resetAllButtons(){
        downButton.setImageResource(R.drawable.downarrowdark);
        upButton.setImageResource(R.drawable.uparrowdark);
        rightButton.setImageResource(R.drawable.rightarrowdark);
        leftButton.setImageResource(R.drawable.leftarrowdark);
    }

    public void leftButtonClick(){
        SoundUtilities.playSound(Game.activity,R.raw.buttonboop3);
        leftButton.setImageResource(R.drawable.leftarrow);
    }

    public void rightButtonClick(){
        SoundUtilities.playSound(Game.activity,R.raw.buttonboop4);
        rightButton.setImageResource(R.drawable.rightarrow);
    }

    public void upButtonClick(){
        SoundUtilities.playSound(Game.activity,R.raw.buttonboop2);
        upButton.setImageResource(R.drawable.uparrow);
    }

    public void downButtonClick(){
        SoundUtilities.playSound(Game.activity,R.raw.buttonboop1);
        downButton.setImageResource(R.drawable.downarrow);
    }

    public void badDownInput(){
        downButton.setImageResource(R.drawable.downarrowred);
    }

    public void badUpInput(){
        upButton.setImageResource(R.drawable.uparrowred);
    }

    public void badRightInput(){
        rightButton.setImageResource(R.drawable.rightarrowred);
    }

    public void badLeftInput(){
        leftButton.setImageResource(R.drawable.leftarrowred);
    }

    public boolean isRoundFinished(){
        if(currentIndex==randomDirectionList.size()){
            //Add points
            countDownTimer.stop();
            newRound();
            return true;
        }
        else return false;
    }

    public void gameOver(){
        if(isRoundFinished()) return; //prevent accidental game overs.
        TextView timeText=findViewById(R.id.MINIGAME_ACTIVITY_SIMON_SAYS_TEXT_VIEW_TIME);
        ColorUtilities.setTextColor(timeText,this,R.color.errorRedMessage);
        TextView scoreValue=findViewById(R.id.MINIGAME_ACTIVITY_SIMON_SAYS_TEXT_VIEW_SCORE_VALUE);
        ColorUtilities.setTextColor(scoreValue,this,R.color.errorRedMessage);
        TextView scoreText=findViewById(R.id.MINIGAME_ACTIVITY_SIMON_SAYS_TEXT_VIEW_SCORE_TEXT);
        ColorUtilities.setTextColor(scoreText,this,R.color.errorRedMessage);


        SoundUtilities.playSound(Game.activity,R.raw.buttonbooperror);
        badDownInput();
        badLeftInput();
        badRightInput();
        badUpInput();

        disableUserInput();
        timer=new TimerWrapper("BadTimer", 1, new IFunction() {
            @Override
            public void execute() {
                Game.player.statistics.score+=score;
                finish();
            }

            @Override
            public void countDownExecute() {

            }
        },false,false,2000);
        countDownTimer.stop();
    }

    public void incrementScore(){
        this.score++;
        String scoreString=Integer.toString(this.score);
        TextView scoreVal=findViewById(R.id.MINIGAME_ACTIVITY_SIMON_SAYS_TEXT_VIEW_SCORE_VALUE);
        scoreVal.setText(scoreString);
    }

}
