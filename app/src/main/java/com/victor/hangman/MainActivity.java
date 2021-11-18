package com.victor.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn, btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.playButton);
        btn1 = (Button)findViewById(R.id.aboutButton);

        btn.setOnClickListener(view -> startActivity(new Intent(this, GameActivity.class)));
        btn1.setOnClickListener(view -> startActivity(new Intent(this, AboutActivity.class)));
    }
}