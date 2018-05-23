package com.example.hck3rz.hck3rz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MatrixDigitalRainActivity extends AppCompatActivity {
    private char[] cars = "+-*/!^'([])#@&?,=$€°|%".toCharArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix_digital_rain_activity);

        TextView textView = (TextView) findViewById(R.id.code_rain_text_view);

        int randomNumber1 = new Random().nextInt(2);

        textView.setText("0\n1\n0\n1\n0");
        float bottomOfScreen = getResources().getDisplayMetrics()
                .heightPixels - (textView.getHeight() * 4);
        //bottomOfScreen is where you want to animate to

        textView.animate()
                .translationY(bottomOfScreen)
                .setInterpolator(new AccelerateInterpolator())
                //.setInterpolator(new BounceInterpolator())
                .setDuration(4000);
    }
    public void onCLick(View v){
        TextView textView = (TextView) findViewById(R.id.code_rain_text_view);

    }
}
