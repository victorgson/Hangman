package com.victor.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {


    boolean didWin = false;
    int tries;
    String wordToGuess;
    Button btn;

    TextView t1, t2, t3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        t1 = (TextView)findViewById(R.id.winLoseText);
        t2 = (TextView)findViewById(R.id.wordWasTextId2);
        t3 = (TextView)findViewById(R.id.wordWasTextId);

        btn = (Button)findViewById(R.id.button);

        btn.setOnClickListener(view -> startActivity(new Intent(this, MainActivity.class)));
        Bundle extras = getIntent().getExtras();

        didWin = extras.getBoolean("VICTORY");
        tries = extras.getInt("TRIES");
        wordToGuess = extras.getString("WORD");


        if(extras == null) {
            System.out.println("EH");
        } else {
            if(didWin == true){
                t1.setText("DU VANN");
            } else {
                t1.setText("DU FÖRLORA!");
            }

            t2.setText("Ordet var: " + wordToGuess);
            t3.setText("Antal försök kvar: " + tries);



        }


    }




}