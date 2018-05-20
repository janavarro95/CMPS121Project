package com.example.hck3rz.hck3rz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import User.IFunction;
import User.TimerWrapper;

public class BootUpActivity extends AppCompatActivity {

    TimerWrapper timer;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boot_up);

        progressBar=findViewById(R.id.BOOTUP_ACTIVITY_PROGRESS_BAR_LOADING_BAR);

        timer=new TimerWrapper("Timer", 4, new IFunction() {
            @Override
            public void execute() {
                logInScreen();
            }

            @Override
            public void countDownExecute() {
                progressBar.incrementProgressBy(1);
            }

        }, false, false, 1000);
    }



    public void logInScreen(){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

}
