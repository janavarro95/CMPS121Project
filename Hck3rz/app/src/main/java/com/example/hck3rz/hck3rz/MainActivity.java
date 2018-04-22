package com.example.hck3rz.hck3rz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static String user_name;
    public static String password;

    private static Button okButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OnClickButtonListener();

    }
    public void OnClickButtonListener(){
        okButton = (Button) findViewById(R.id.okButton);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // store the username and password in static strings
                // so that you can access them from other activities
                EditText name = findViewById(R.id.userNameEditText);
                user_name = name.getText().toString();

                EditText psw = findViewById(R.id.passwordEditText);
                password = psw.getText().toString();

                Intent intent = new Intent(".DesktopActivity");
                startActivity(intent);
            }
        });
    }

}

