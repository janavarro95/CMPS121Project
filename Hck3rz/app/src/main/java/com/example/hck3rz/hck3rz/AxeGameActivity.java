package com.example.hck3rz.hck3rz;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import User.Game;
import User.IFunction;
import User.TimerWrapper;
import Utilities.SoundUtilities;

/* There are 3 text fields
 * Each one has a width of about 12 numbers
 * The player will always be on the bottom on either the right or the left
 * The player will swipe either left or right to play the game
*/
public class AxeGameActivity extends AppCompatActivity {

    TimerWrapper finishTimer; //a timer that handles the game over screen.
    TimerWrapper quickTimer; //reflex based timer that kills the player if they take about 3 seconds to react.
    TimerWrapper winTimer; //A timer that handles surviving for 30 seconds.

    int score;

    // References
    TextView leftSide;
    TextView centerSide;
    TextView rightSide;

    Button left;
    Button right;

    // Game strings
    // The string the represents the tree
    String LOG =    "138888888831" +
                    "138888888831" +
                    "138888888831" +
                    "138888888831";

    String BRANCH =    "------------------------" +
                       "138888888831" +
                       "138888888831" +
                       "------------------------";
    // The string the represents the player
    String PLAYER = "------000000------" +
                    "--0055005500--" +
                    "--0000----0000--" +
                    "----00000000----";
    // The string the represents the empty space
    String EMPTY =  "------------" +
                    "------------" +
                    "------------" +
                    "------------" +
                    "------------" +
                    "------------" +
                    "------------" +
                    "------------";

    // Game variables
    // The center never changes
    int num_of_segments = 7;
    boolean direction = false;
    int[] left_index  = new int[num_of_segments];
    int[] right_index = new int[num_of_segments];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Game.activity=this;
        Game.SetFullScreen();
        setContentView(R.layout.activity_axe_game_activity);
        score=0;

        // get the references
        leftSide = findViewById(R.id.GameViewL);
        centerSide = findViewById(R.id.GameViewC);
        rightSide = findViewById(R.id.GameViewR);

        left = findViewById(R.id.LeftArrow);
        right = findViewById(R.id.RightArrow);

        left.setVisibility(View.VISIBLE);
        left.setBackgroundColor(android.graphics.Color.TRANSPARENT);
        right.setVisibility(View.VISIBLE);
        right.setBackgroundColor(android.graphics.Color.TRANSPARENT);

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                direction = false;
                increment_segments();
                print_segments();
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                direction = true;
                increment_segments();
                print_segments();
            }
        });

        random_start();
        print_segments();
        winTimer=new TimerWrapper("winTimer", 1, new IFunction() {
            @Override
            public void execute() {
                win();
            }

            @Override
            public void countDownExecute() {

            }
        },false,false,30000);

        quickTimer=new TimerWrapper("quickTime", 1, new IFunction() {
            @Override
            public void execute() {
                rightSide.setTextColor(Color.RED);
                leftSide.setTextColor(Color.RED);
                gameOver();
            }

            @Override
            public void countDownExecute() {

            }
        },false,false,3000);
    }
    public void random_start(){
        for(int i = 0; i < num_of_segments - 1; i++){
            Random rand = new Random();
            left_index[i] = rand.nextInt(2);// 0 - 1
        }
        // then do right based on left
        for(int i = 0; i < num_of_segments - 1; i++){
            Random rand = new Random();
            if(left_index[i] != 1){
                right_index[i] = rand.nextInt(2);// 0 - 1
            }else{
                right_index[i] = 0;
            }
        }
        left_index [num_of_segments - 1] = 2;
        right_index[num_of_segments - 1] = 2;
    }
    public void increment_segments(){
        // check for a few things
        // check if the player hits a branch
        // move all the branches down 1
        // generate a new random branch
        rightSide.setTextColor(Color.GREEN);
        leftSide.setTextColor(Color.GREEN);
        boolean can_move = true;
        if(direction){
            if(right_index[num_of_segments - 2] == 1){
                // no can do
                can_move = false;
                // make the right red
                rightSide.setTextColor(Color.RED);
                gameOver();
            }
        }else{
            if(left_index[num_of_segments - 2] == 1){
                // no can do
                can_move = false;
                // make the left red
                leftSide.setTextColor(Color.RED);
                gameOver();
            }
        }
        if(can_move){
            // for now just move everything down 1
            for(int i = num_of_segments - 2; i > 0; i --){
                left_index[i] = left_index[i - 1];
                right_index[i] = right_index[i - 1];
            }
            score+=3;
            // randomly generate the top segment
            Random rand = new Random();
            left_index[0] = rand.nextInt(2);// 0 - 1
            if(left_index[0] != 1){
                right_index[0] = rand.nextInt(2);// 0 - 1
            }else{
                right_index[0] = 0;
            }
            //If they take too long to get to the next segment just game over them.
            quickTimer=new TimerWrapper("quickTime", 1, new IFunction() {
                @Override
                public void execute() {
                    rightSide.setTextColor(Color.RED);
                    leftSide.setTextColor(Color.RED);
                    gameOver();
                }

                @Override
                public void countDownExecute() {

                }
            },false,false,3000);
        }
    }
    public void print_segments(){
        String center_str = "";
        String left_str   = "";
        String right_str  = "";

        // loop through all the segments
        for(int i = 0; i < num_of_segments; i ++){
            center_str += LOG;
            if(i == num_of_segments - 1){
                if(direction){
                    left_str  += EMPTY;
                    right_str += PLAYER;
                }else{
                    left_str  += PLAYER;
                    right_str += EMPTY;
                }
            }else {
                if(left_index[i] == 0){
                    left_str += EMPTY;
                }else if(left_index[i] == 1){
                    left_str += BRANCH;
                }
                if(right_index[i] == 0){
                    right_str += EMPTY;
                }else if(right_index[i] == 1){
                    right_str += BRANCH;
                }
            }
        }
        centerSide.setText(center_str);
        leftSide.setText(left_str);
        rightSide.setText(right_str);
    }

    public void gameOver(){
        this.finishTimer=new TimerWrapper("gameOverAxe", 1, new IFunction() {
            @Override
            public void execute() {
                Game.player.statistics.score+=score;
                finish();
            }

            @Override
            public void countDownExecute() {

            }
        },false,false,2000);
    }

    public void win(){
        try {
            this.quickTimer.stop();
        }
        catch (Exception err){

        }
        try {
            this.finishTimer.stop();
        }
        catch (Exception err){

        }
        TimerWrapper winCooldown=new TimerWrapper("winCooldown", 1, new IFunction() {
            @Override
            public void execute() {
                SoundUtilities.playSound(Game.activity,R.raw.microsoft_windows_xp_startup_sound);
                Game.player.statistics.score+=score+25;
                try {
                    Game.player.save(Game.getCurrentAppContext());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finish();
            }

            @Override
            public void countDownExecute() {

            }
        },false,false,3000);
    }
}
