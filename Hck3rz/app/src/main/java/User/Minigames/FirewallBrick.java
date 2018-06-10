package User.Minigames;

import android.support.v4.math.MathUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.hck3rz.hck3rz.FolderActivities.FirewallBreakActivity;
import com.example.hck3rz.hck3rz.R;

import User.Game;
import Utilities.SoundUtilities;

public class FirewallBrick {

    ImageView image;
    int health;

    FirewallBrick instance;

    FirewallBreakActivity activity;

    public FirewallBrick(ImageView button, final FirewallBreakActivity activity){
        health = Utilities.Math.Random.getRandomExclusive(1,4);
        image=button;
        instance=this;
        this.activity=activity;
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                instance.health--;
                setHealthColor();
                playSound();
                activity.areAllDead();
            }
        });
    }

    public void setHealthColor(){
        if(this.health==3) this.image.setImageResource(R.drawable.green_brick_button);
        if(this.health==2) this.image.setImageResource(R.drawable.yellow_brick_button);
        if(this.health==1) this.image.setImageResource(R.drawable.red_brick_button);
        if(this.health<=0){
            this.image.setVisibility(View.INVISIBLE);
            this.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Empty onclick listener
                }
            });
        }
    }

    public boolean isDead(){
        if(this.health<=0) return true;
        else return false;
    }

    private void playSound(){
        int i=Utilities.Math.Random.getRandomExclusive(0,4);
        if(i==0) SoundUtilities.playSound(Game.activity,R.raw.buttonboop1);
        if(i==1) SoundUtilities.playSound(Game.activity,R.raw.buttonboop2);
        if(i==2) SoundUtilities.playSound(Game.activity,R.raw.buttonboop3);
        if(i==3) SoundUtilities.playSound(Game.activity,R.raw.buttonboop4);
    }


}
