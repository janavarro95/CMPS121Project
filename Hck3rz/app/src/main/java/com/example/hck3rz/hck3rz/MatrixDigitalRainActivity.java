package com.example.hck3rz.hck3rz;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.os.Handler;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MatrixDigitalRainActivity extends AppCompatActivity {
    private char[] cars = "+-*/!^'([])#@&?,=$€°|%".toCharArray();
    private Handler handler = new Handler();
    private final int TIME_DELAY = 3000;
    int[] textViewIds={R.id.code_rain_text_view1,R.id.code_rain_text_view2,R.id.code_rain_text_view3,R.id.code_rain_text_view4,
            R.id.code_rain_text_view5,R.id.code_rain_text_view6,R.id.code_rain_text_view7,R.id.code_rain_text_view8,
            R.id.code_rain_text_view9,R.id.code_rain_text_view10};
    int textViewAmount = textViewIds.length;
    int last5CodePosAmount = 5;
    int pointCounter =0;
    TextView winTextView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix_digital_rain_activity);

        //get height and width of screen
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int bottomOfScreen = displayMetrics.heightPixels;//height
        int width = displayMetrics.widthPixels;
        int resetYPosition=-300;
        int randomNumber1 = new Random().nextInt(2);
        winTextView = (TextView)findViewById(R.id.code_rain_win_text_view);


        //Make ten code text views and link to their xml id
        List<TextView> codeList = new ArrayList<TextView>();
        for(int i=0; i<textViewAmount; i++){
            codeList.add((TextView)findViewById(textViewIds[i]));
            codeList.get(i).setText(i+"\n"+i+"\n"+i+"\n"+i+"\n"+i);
            codeList.get(i).measure(0,0);
            codeList.get(i).setY(-codeList.get(i).getMeasuredHeight());
        }

//        final float bottomOfScreen = getResources().getDisplayMetrics()
//                .heightPixels - (textView.getHeight() * 4);


        //Log.d("width", String.valueOf(width));

        /*
        * 1. N/A Get height of code text view
        * 2. DONES et code above screen DONE
        * 3. DONE Make ten code text views and link to their xml id
        * 4. DONE change string code to their corresponding index number to see difference of each code
        * 5. DONE Make each code drop at random x position without overlaying (
        *   -make sure current code is not set to any of the last 5 code
        * 6.Make each of the code fall at random time
        * 7.Make code symbols change
        * */

        //list to record last 5 codes positions
        List<Float> last5CodePos = new ArrayList<Float>();

        //code fall to bottom of screen
        for(int i=0; i<codeList.size(); i++){
            //random x position
            codeList.get(i).measure(0,0);
            int codeWidth=codeList.get(i).getMeasuredWidth();
            Log.d("codeWidth",String.valueOf(codeWidth));

            codeList.get(i).setX(new Random().nextInt(width+1)-width/2);//range[-width/2,width/2]

            //check if there are code in last5CodePos
            //loop through last5CodePos and prevent current code from setting the same postion
            for(int j = 0; j < last5CodePos.size(); j++){
                //check if the current code is within the last code position
                if(Math.abs(Math.abs(codeList.get(i).getX()) - Math.abs(last5CodePos.get(j))) <= codeList.get(i).getMeasuredWidth()){
                    codeList.get(i).setX(new Random().nextInt((width+1)-codeWidth)-width/2+codeWidth);
                    j=0;
                }
            }

            //Remember last 5 codes position and prevent current code from overlapping them
            /*remove 1st element if last5CodePos has 5 elements*/
            if(last5CodePos.size() == last5CodePosAmount ){
                last5CodePos.remove(0);
            }
            last5CodePos.add(codeList.get(i).getX());

            //delay code random time
            codeDelay(codeList.get(i), bottomOfScreen);
        }

    }


    public void codeDelay(final View textView, final int bottomOfScreen){
        handler.postDelayed(new Runnable() {
            public void run() {
                codeFall(textView, bottomOfScreen);
            }
        }, new Random().nextInt(4000)+1000);
    }

    public void codeResetPosition(final View textView, final float bottomOfScreen){
        handler.postDelayed(new Runnable() {
            public void run() {
                //if to stop recursion

                textView.setY(0);
                codeResetPosition(textView, bottomOfScreen);
            }
        }, TIME_DELAY);
    }

    public void codeFall(View textView, float bottomOfScreen){
        textView.animate()
                .translationY(bottomOfScreen)
                .setInterpolator(new AccelerateInterpolator())
                //.setInterpolator(new BounceInterpolator())
                .setDuration(5000);
    }

    public void onClick(View v){
        pointCounter++;
        v.setVisibility(View.GONE);
        checkIfWon(pointCounter);
    }

    public void checkIfWon(int pointCounter){
        Log.d("point",String.valueOf(pointCounter));
        if(pointCounter == textViewAmount){
            Log.d("won","Win");
            winTextView.setVisibility(View.VISIBLE);
        }
    }
}
