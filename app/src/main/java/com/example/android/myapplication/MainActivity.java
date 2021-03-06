package com.example.android.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import java.io.InputStream;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

/* Pokeapi imports */
import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;
import me.sargunvohra.lib.pokekotlin.model.Pokemon;
import me.sargunvohra.lib.pokekotlin.model.PokemonSpecies;
import me.sargunvohra.lib.pokekotlin.model.PokemonSprites;

public class MainActivity extends AppCompatActivity {
    PokeApi pokeApi = new PokeApiClient();
    final PokeObject[] po = {null, null};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @SuppressLint("SetTextI18n")
    public void onPokemon1AttackButtonClick(View v) {
        if (po[0] != null && po[1] != null) {
            po[1].health.takeDamage((int) (Math.random() * 50));

            TextView pokeHP2 = (TextView) findViewById(R.id.pokemon_2_HP);
            pokeHP2.setText(po[1].health.getCurrentHP() + " HP");
        }
    }

    @SuppressLint("SetTextI18n")
    public void onPokemon2AttackButtonClick(View v) {
        if (po[0] != null && po[1] != null) {
            po[0].health.takeDamage((int) (Math.random() * 50));

            TextView pokeHP1 = (TextView) findViewById(R.id.pokemon_1_HP);
            pokeHP1.setText(po[0].health.getCurrentHP() + " HP");
        }
    }

    @SuppressLint("SetTextI18n")
    public void onPokemon1HealButtonClick(View v) {
        if (po[0] != null && po[1] != null) {
            po[0].health.healDamage((int) (Math.random() * 50));

            TextView pokeHP1 = (TextView) findViewById(R.id.pokemon_1_HP);
            pokeHP1.setText(po[0].health.getCurrentHP() + " HP");
        }
    }

    @SuppressLint("SetTextI18n")
    public void onPokemon2HealButtonClick(View v) {
        if (po[0] != null && po[1] != null) {
            po[1].health.healDamage((int) (Math.random() * 30));

            TextView pokeHP2 = (TextView) findViewById(R.id.pokemon_2_HP);
            pokeHP2.setText(po[1].health.getCurrentHP() + " HP");
        }
    }


    // the @SuppressLint allows string literals in the onButtonClick function
    @SuppressLint("SetTextI18n")
    public void onGenerateButtonClick(View v) {
        Random rand = new Random();
        // final Integer[] myPokeNums = {null, null};
//        final int myPokeNum1 = rand.nextInt(151) + 1; // get a random number between 1 and 151 inclusive
//        final int myPokeNum2 = rand.nextInt(151) + 1; // get a random number between 1 and 151 inclusive
        //final boolean []isShiny = {rand.nextDouble() <= 0.25, rand.nextDouble() <= 0.25} ;


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                for (int i = 0; i < 2; i++) {
                    po[i] = new PokeObject(rand.nextInt(151) + 1);
                }
                ;
            }
        });


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
        TextView pokeName1 = (TextView) findViewById(R.id.pokemon_1_name);
        TextView pokeName2 = (TextView) findViewById(R.id.pokemon_2_name);

        pokeName1.setText(po[0].getName());
        pokeName2.setText(po[1].getName());

        ImageView image1 = (ImageView) findViewById(R.id.pokemon_1_sprite);
        ImageView image2 = (ImageView) findViewById(R.id.pokemon_2_sprite);

        // set the image accordingly
        image1.setImageBitmap(po[0].getBitmap());
        image2.setImageBitmap(po[1].getBitmap());

        TextView pokeHP1 = (TextView) findViewById(R.id.pokemon_1_HP);
        TextView pokeHP2 = (TextView) findViewById(R.id.pokemon_2_HP);

        pokeHP1.setText(po[0].health.getCurrentHP() + " HP");
        pokeHP2.setText(po[1].health.getCurrentHP() + " HP");


    } // end onButtonClick
}