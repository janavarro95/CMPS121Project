package com.example.hck3rz.hck3rz;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import User.Game;

public class OptionsActivity extends AppCompatActivity {

    private Button muteButton;
    private SeekBar volumBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Game.activity=this;
        Game.SetFullScreen();
        setContentView(R.layout.activity_options);


        muteButton=findViewById(R.id.OPTIONS_ACTIVITY_BUTTON_MUTE);
        volumBar = findViewById(R.id.OPTIONS_ACTIVITY_VOLUME_SLIDER);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            volumBar.setMin(0);
            volumBar.setMax(100);
        }
        volumBar.setProgress((int)(Game.options.soundOptions.volume) * 100);

        volumBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int value= seekBar.getProgress();
                float amount = value/100;
                Game.options.soundOptions.setVolume(amount);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        setMuteButtonText();
    }

    /**
     * Toggles the muted setting for the game.
     * @param view
     */
    public void muteToggle(View view){
        Game.options.soundOptions.toggleMute();
        setMuteButtonText();
    }

    /**
     * Sets the appropriate text for the mute button.
     */
    private void setMuteButtonText(){
        if(Game.options.soundOptions.muted){
            muteButton.setText("Unmute");
        }
        else{
            muteButton.setText("Mute");
        }
    }
}
