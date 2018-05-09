package com.example.hck3rz.hck3rz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class AxeGameActivity extends AppCompatActivity {
    private String[] contents = new String[4 * 6]; // 6 is collumb height
    private TextView tv;  // the text view that contains all the console text
    private Button left;  // the left button for input;
    private Button right; // the right button for input;
    private static final String LOG   = "100001";
    private static final String EMPTY = "      ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_axe_game_activity);

        // get the references
        tv = findViewById(R.id.GameView);
        left = findViewById(R.id.LeftArrow);
        right = findViewById(R.id.RightArrow);

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // do left action
                tv.setText("Left");
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // do right action
                tv.setText("Right");
            }
        });

        // at start instantiate 10 logs
        start();
    }
    private void start(){
        for(int i = 0; i < contents.length; i += 6) {
            if(i == 0){
                em_log_em(i);
            }else{
                // randomize it
                Random ran = new Random();
                int x = ran.nextInt(2 - 0);
                switch(x){
                    case 0:
                        // its all log
                        em_log_em(i);
                        break;
                    case 1:
                        em_log_em(i);
                        break;
                    case 2:
                        em_log_em(i);
                        break;
                }
            }

        }
        tv.setText("");
        for(int i = 0; i < contents.length; i ++){
            tv.setText(tv.getText() + contents[i]);
        }
    }
    private void em_log_em(int i){
        contents[i    ] = EMPTY + LOG + EMPTY + "\n";
        contents[i + 1] = EMPTY + LOG + EMPTY + "\n";
        contents[i + 2] = EMPTY + LOG + EMPTY + "\n";
        contents[i + 3] = EMPTY + LOG + EMPTY + "\n";
        contents[i + 4] = EMPTY + LOG + EMPTY + "\n";
        contents[i + 5] = EMPTY + LOG + EMPTY + "\n";
    }
}
