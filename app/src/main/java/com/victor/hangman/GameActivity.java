package com.victor.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class GameActivity extends AppCompatActivity {

    ImageView image;
    EditText guess,guessEdit;
    Button guessBtn;
    TextView triesText, wordText, letterGuessText;
    int tries = 10;
    String wordToGuess;
    Game g;
    boolean guessWasRight;


    ArrayList<Character> guessedLetters = new ArrayList<Character>();
    ArrayList<String> wordPlaceholder = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        guess = (EditText) findViewById(R.id.editTextGuess);
        guessBtn = (Button) findViewById(R.id.guessBtn);
        triesText = (TextView)findViewById(R.id.textTriesId);
        wordText = (TextView)findViewById(R.id.wordTextId);
        letterGuessText = (TextView)findViewById(R.id.letterGuessId);
        image = (ImageView)findViewById(R.id.hangmanImagesId);
        guessBtn.setOnClickListener(view -> onGuess(Character.toLowerCase(guess.getText().charAt(0))));

        g = new Game();
        wordToGuess = g.getRandomWord().toLowerCase(Locale.ROOT);
        System.out.println(wordToGuess);
        for(int i = 0; i < wordToGuess.length(); i++){
            wordPlaceholder.add(i,"_");
        }
        wordText.setText(wordPlaceholder.toString());

        updateImage();


    }



    void onGuess(char guess){
        //System.out.println(guessesLetters.toString());
        System.out.println("onGuess ran!");

        if(guessedLetters.contains(guess)){
            Toast.makeText(this, "Already guessed on " + guess , Toast.LENGTH_SHORT).show();
        } else {
            guessedLetters.add(guess);
        }


        if(tries > 0) {
            gameLogic(guess);
            if(guessWasRight != true){
                tries--;
                updateImage();
            }


            updateUI();
        } else {
            Intent i = new Intent(this, ResultActivity.class);
            i.putExtra("VICTORY", false);
            i.putExtra("WORD", wordToGuess);
            i.putExtra("TRIES", tries);
            startActivity(i);
        }
    }

    void gameLogic(char guess){
        //breaking down wordToGuess to letters
    guessWasRight = false;
        for(int i = 0; i < wordToGuess.length(); i++){
            System.out.println(guess);
            System.out.println(wordToGuess.charAt(i));
            if(guess == wordToGuess.charAt(i)){
                System.out.println("The letter matches this one");
                wordPlaceholder.remove(i);
                wordPlaceholder.add(i,Character.toString(guess));
                wordText.setText(wordPlaceholder.toString());
                guessWasRight = true;
            } else {
                System.out.println("The letter doesn't match this one");
            }
        }

        checkWinning();
    }

    void checkWinning(){
        String finalWord = "";
        for(int i = 0; i < wordPlaceholder.size(); i++){
            finalWord += wordPlaceholder.get(i);
        }
        if(finalWord.equals(wordToGuess)) {
            Intent i = new Intent(this, ResultActivity.class);
            i.putExtra("VICTORY", true);
            i.putExtra("TRIES", tries);
            i.putExtra("WORD", wordToGuess);
            startActivity(i);

        }
    }


    void updateUI(){
        // Uppdaterar texten
        triesText.setText(tries + " försök kvar");
        letterGuessText.setText(guessedLetters.toString());

    }

    void updateImage(){

        // Har inte bilder för att visa en riktig hangman så detta får visa att jag fattar konceptet
        if(tries > 7){
            image.setImageResource(R.drawable.hangman);
        } else if(tries > 4) {
            image.setImageResource(R.drawable.hangman2);
        }
        else if(tries > 0) {
            image.setImageResource(R.drawable.hangman3);
        }


    }
}