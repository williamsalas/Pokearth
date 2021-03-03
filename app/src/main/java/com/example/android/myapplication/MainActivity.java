package com.example.android.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import java.io.InputStream;
import java.net.URL;
import java.util.Random;

/* Pokeapi imports */
import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;
import me.sargunvohra.lib.pokekotlin.model.Pokemon;
import me.sargunvohra.lib.pokekotlin.model.PokemonSpecies;
import me.sargunvohra.lib.pokekotlin.model.PokemonSprites;

public class MainActivity extends AppCompatActivity {
    PokeApi pokeApi = new PokeApiClient();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // the @SuppressLint allows string literals in the onButtonClick function
    @SuppressLint("SetTextI18n")
    public void onButtonClick(View v) {

        Random rand = new Random();
        int myPokeNum = rand.nextInt(151) + 1; // get a random number between 1 and 151 inclusive
        boolean isShiny = false;
        if (rand.nextDouble() <= 0.25)
            isShiny = true;

        final Pokemon[] myPoke = {null};
        final PokemonSpecies[] myPokeSpecies = {null};
        final Bitmap[] bitmap = {null};

        boolean finalShiny = isShiny; // had to use for some reason
        Thread thread = new Thread() {

            @Override
            public void run() {
                try {
                    // grab reference to the Pokemon, PokemonSpecies, and Sprites via api call
                    // api calls must happen inside threads
                    myPoke[0] = pokeApi.getPokemon(myPokeNum);
                    myPokeSpecies[0] = pokeApi.getPokemonSpecies(myPokeNum);
                    PokemonSprites myPokeSprites = myPoke[0].getSprites();
                    String frontSprite;
                    if (finalShiny)
                        frontSprite = myPokeSprites.getFrontShiny();
                    else
                        frontSprite = myPokeSprites.getFrontDefault();
                    bitmap[0] = BitmapFactory.decodeStream((InputStream) new URL(frontSprite).getContent()); // networking
                } // end run
                catch (Exception e) {
                    e.printStackTrace();
                } // end catch
            }
        };

        thread.start(); // start the thread
        // wait for thread to finish before continuing
        try {
            thread.join();
        } catch (InterruptedException e) {
            // oops
        }

        /*
        If no data populated display error message
         */

        // grab reference to relevant data fields
        TextView tv1 = (TextView) findViewById(R.id.textView);
        TextView tv2 = (TextView) findViewById(R.id.textView2);
        TextView tv3 = (TextView) findViewById(R.id.textView3);
        ImageView i = (ImageView) findViewById(R.id.logoImageView);

        // set the appropriate sprite
        i.setImageBitmap(bitmap[0]);

        // set the text fields appropriately with API calls
        tv1.setText("Num: " + myPokeSpecies[0].getId());
        if (finalShiny)
            tv2.setText("shiny " + myPokeSpecies[0].getName());
        else
            tv2.setText(myPokeSpecies[0].getName());
        tv3.setText("Capture rate: " + myPokeSpecies[0].getCaptureRate());

    } // end onButtonClick
}