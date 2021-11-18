package com.victor.hangman;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

public class Game {


    ArrayList<String> words = new ArrayList<String>();

    public Game(){

    }

    public String getRandomWord(){

        words.add("Basket");
        words.add("Öl");
        words.add("Fotboll");
        words.add("Programmera");
        words.add("Eld");

        Random r1 = new Random();
        int n = r1.nextInt(words.size());

        return words.get(n);
    }
}
