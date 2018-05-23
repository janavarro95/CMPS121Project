package com.example.hck3rz.hck3rz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import User.Game;

public class TerminalActivity extends AppCompatActivity {
    private String terminal_content = "Welcome!";
    private final String[] boot_sequence = {"Booting hackOS1.0","Success"};
    private final static String TAG = ">: ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Game.activity=this;
        Game.SetFullScreen();
        setContentView(R.layout.activity_terminal);


        // get the display text
        TextView tv = findViewById(R.id.TerminalView);
        // set the contents
        boot(tv);

    }
    private void console_line(TextView tv){
        tv.setText(tv.getText().toString() + "| ------------------------------------------------------------------ |" + "\n");
    }
    private void boot(TextView tv){
        String display = "";
        display += TAG + terminal_content + "\n";
        for(int i = 0; i < boot_sequence.length; i++){
            display += TAG +boot_sequence[i] + "\n";
        }
        tv.setText(display);
        console_line(tv);
    }
}
