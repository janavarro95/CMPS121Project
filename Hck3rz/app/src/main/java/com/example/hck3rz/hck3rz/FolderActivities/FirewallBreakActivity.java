package com.example.hck3rz.hck3rz.FolderActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hck3rz.hck3rz.DesktopActivity;
import com.example.hck3rz.hck3rz.R;

import java.util.ArrayList;

import User.DialogueText;
import User.Game;
import User.IFunction;
import User.Minigames.FirewallBrick;
import User.PostMan;
import User.TimerWrapper;
import Utilities.ColorUtilities;
import Utilities.SoundUtilities;

public class FirewallBreakActivity extends AppCompatActivity {

    public static ArrayList<FirewallBrick> bricks;

    private static ImageView smile;

    TimerWrapper finishedTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firewall_break);
        bricks=new ArrayList<>();
        Game.activity=this;

        ImageView b1=findViewById(R.id.FIREWALL_ACTIVITY_FIREWALL_BRICK_1);
        ImageView b2=findViewById(R.id.FIREWALL_ACTIVITY_FIREWALL_BRICK_2);
        ImageView b3=findViewById(R.id.FIREWALL_ACTIVITY_FIREWALL_BRICK_3);
        ImageView b4=findViewById(R.id.FIREWALL_ACTIVITY_FIREWALL_BRICK_4);
        ImageView b5=findViewById(R.id.FIREWALL_ACTIVITY_FIREWALL_BRICK_5);
        ImageView b6=findViewById(R.id.FIREWALL_ACTIVITY_FIREWALL_BRICK_6);
        ImageView b7=findViewById(R.id.FIREWALL_ACTIVITY_FIREWALL_BRICK_7);
        ImageView b8=findViewById(R.id.FIREWALL_ACTIVITY_FIREWALL_BRICK_8);
        ImageView b9=findViewById(R.id.FIREWALL_ACTIVITY_FIREWALL_BRICK_9);
        ImageView b10=findViewById(R.id.FIREWALL_ACTIVITY_FIREWALL_BRICK_10);
        ImageView b11=findViewById(R.id.FIREWALL_ACTIVITY_FIREWALL_BRICK_11);
        ImageView b12=findViewById(R.id.FIREWALL_ACTIVITY_FIREWALL_BRICK_12);
        ImageView b13=findViewById(R.id.FIREWALL_ACTIVITY_FIREWALL_BRICK_13);
        ImageView b14=findViewById(R.id.FIREWALL_ACTIVITY_FIREWALL_BRICK_14);
        ImageView b15=findViewById(R.id.FIREWALL_ACTIVITY_FIREWALL_BRICK_15);

        FirewallBrick B1=new FirewallBrick(b1,this);
        FirewallBrick B2=new FirewallBrick(b2,this);
        FirewallBrick B3=new FirewallBrick(b3,this);
        FirewallBrick B4=new FirewallBrick(b4,this);
        FirewallBrick B5=new FirewallBrick(b5,this);
        FirewallBrick B6=new FirewallBrick(b6,this);
        FirewallBrick B7=new FirewallBrick(b7,this);
        FirewallBrick B8=new FirewallBrick(b8,this);
        FirewallBrick B9=new FirewallBrick(b9,this);
        FirewallBrick B10=new FirewallBrick(b10,this);
        FirewallBrick B11=new FirewallBrick(b11,this);
        FirewallBrick B12=new FirewallBrick(b12,this);
        FirewallBrick B13=new FirewallBrick(b13,this);
        FirewallBrick B14=new FirewallBrick(b14,this);
        FirewallBrick B15=new FirewallBrick(b15,this);

        //Add all bricks to array list.

        bricks.add(B1);
        bricks.add(B2);
        bricks.add(B3);
        bricks.add(B4);
        bricks.add(B5);
        bricks.add(B6);
        bricks.add(B7);
        bricks.add(B8);
        bricks.add(B9);
        bricks.add(B10);
        bricks.add(B11);
        bricks.add(B12);
        bricks.add(B13);
        bricks.add(B14);
        bricks.add(B15);

        smile=findViewById(R.id.FIREWALL_ACTIVITY_IMAGEVIEW_CREEPYSMILE);
        smile.setVisibility(View.INVISIBLE);
        //Get all button ids, make bricks, add to list.

        for(int i=0;i<bricks.size();i++){
            bricks.get(i).setHealthColor();
        }

    }


    public boolean areAllDead(){
        for (int i=0; i<bricks.size();i++){
            if(!bricks.get(i).isDead()) return false;
        }
        TextView t=Game.activity.findViewById(R.id.FIREWALL_ACTIVITY_TEXT_VIEW_WIN_TEXT);
        ColorUtilities.setTextColor(t,Game.activity,R.color.errorRedMessage);
        final DialogueText text=new DialogueText(t, PostMan.readFromFile(Game.getCurrentAppContext(),"Tutorial_FirewallBroken.txt"),DialogueText.SPEED_MEDIUM);
        text.getTextTimer().function=new IFunction() {
            @Override
            public void execute() {
                smile.setVisibility(View.VISIBLE);
                SoundUtilities.playSound(Game.activity,R.raw.skull_kid_laughing);
                Game.player.hasSeenTutorial=true;
                Game.player.statistics.score+=100;
                finishedTimer=new TimerWrapper("finished", 1, new IFunction() {
                    @Override
                    public void execute() {
                        Intent i=new Intent(Game.activity, DesktopActivity.class);
                        startActivity(i);
                    }

                    @Override
                    public void countDownExecute() {

                    }
                },false,false,3000);
            }

            @Override
            public void countDownExecute() {
                text.printNextDialogueCharacter();
            }
        };
        text.startDialogue();
        //Do more logic here to finish the game and go to the desktop.
        //Make the creepy win text appear. Make it color red.
        //Make the creepy laughter sound appear.
        //Also make sure that we save the variable that says the player finished the tutorial.
        return true;
    }
}
