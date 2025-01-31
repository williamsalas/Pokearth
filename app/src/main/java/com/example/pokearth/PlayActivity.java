package com.example.pokearth;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pokearth.DB.Party;
import com.example.pokearth.DB.PartyDataSource;
import com.example.pokearth.pokedex.LoadingPageDialog;
import com.example.pokearth.pokedex.PokedexActivity;
import com.example.pokearth.storage.PokemonStorageActivity;

import java.util.List;

public class PlayActivity extends AppCompatActivity {

    private Button pokedexButton, storageButton;
    private ImageView playerImageView, pokemonImageView1, pokemonImageView2, pokemonImageView3, pokemonImageView4, pokemonImageView5, pokemonImageView6;
    final PokemonObject[] po = {null, null, null, null, null, null};
    private PartyDataSource dataSource;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_page);


        dataSource = new PartyDataSource(this);

        GenerateSaved runnable = new GenerateSaved();
        new Thread(runnable).start();

        LoadingPageDialog loadingPageDialog = new LoadingPageDialog(PlayActivity.this);

        playerImageView = findViewById(R.id.playerSprite);
        playerImageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PartyActivity.class);
                startActivity(intent);
            }
        });

        pokemonImageView1 = findViewById(R.id.partySprite1);
        pokemonImageView1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PartyActivity.class);
                startActivity(intent);
            }
        });

        pokemonImageView2 = findViewById(R.id.partySprite2);
        pokemonImageView2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PartyActivity.class);
                startActivity(intent);
            }
        });

        pokemonImageView3 = findViewById(R.id.partySprite3);
        pokemonImageView3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PartyActivity.class);
                startActivity(intent);
            }
        });

        pokemonImageView4 = findViewById(R.id.partySprite4);
        pokemonImageView4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PartyActivity.class);
                startActivity(intent);
            }
        });

        pokemonImageView5 = findViewById(R.id.partySprite5);
        pokemonImageView5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PartyActivity.class);
                startActivity(intent);
            }
        });

        pokemonImageView6 = findViewById(R.id.partySprite6);
        pokemonImageView6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PartyActivity.class);
                startActivity(intent);
            }
        });

        storageButton = findViewById(R.id.storage_Button);
        storageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStorageActivity(v);
            }
        });

        pokedexButton = findViewById(R.id.pokemon_select_button);
        pokedexButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                loadingPageDialog.startLoadingDialog();
                Intent intent = new Intent(PlayActivity.this, PokedexActivity.class);
                startActivity(intent);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingPageDialog.dismissDialog();
                    }
                }, 10000);
            }
        });

    }

    public void openStorageActivity(View v)
    {
        Intent intent = new Intent(PlayActivity.this, PokemonStorageActivity.class);
        startActivity(intent);
    }

    public void openFightActivity(View v) {
        Intent intent = new Intent(PlayActivity.this, FightActivity.class);
        startActivity(intent);
    }


    public void openBiomeActivity(View v) {
        Intent intent = new Intent(PlayActivity.this, BiomeActivity.class);
        startActivity(intent);
    }

    public void openMainActivity(View v) {
        Intent intent = new Intent(PlayActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private class GenerateSaved implements Runnable {

        @Override
        public void run() {
            List<Party> partyPokemon = dataSource.getAllPokemon();
            Looper.prepare();
            int nullCounter = 0;
            for (int x = 0; x < 6; x++) {
                if (partyPokemon.size() > 0 && partyPokemon.get(x).getPokemonId() > 0)
                    po[x] = new PokemonObject(partyPokemon.get(x).getPokemonId(), partyPokemon.get(x).getPokeObject(), partyPokemon.get(x).getPokeSpecies(), partyPokemon.get(x).getBitmapString());
                else {
                    po[x] = null;
                    nullCounter++;
                }
            }
            if (nullCounter != 6) {
                runOnUiThread(new Runnable() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void run() {

                        String typeColorString;
                        int typeColorInt;
                        ColorFilter filter;

                        ImageView pokemonImageView1 = (ImageView) findViewById(R.id.partySprite1);
                        if (po[0] != null) {
                            pokemonImageView1.setImageBitmap(po[0].getBitmap());

                            typeColorString = po[0].getTypeColorString(0);
                            typeColorInt = Color.parseColor(typeColorString);
                            filter = new LightingColorFilter(Color.BLACK, typeColorInt);
                            pokemonImageView1.getBackground().setColorFilter(filter);
                            pokemonImageView1.setVisibility(View.VISIBLE);
                        } else {
                            pokemonImageView1.setImageBitmap(null);
                        }

                        ImageView pokemonImageView2 = (ImageView) findViewById(R.id.partySprite2);
                        if (po[1] != null) {
                            pokemonImageView2.setImageBitmap(po[1].getBitmap());

                            typeColorString = po[1].getTypeColorString(0);
                            typeColorInt = Color.parseColor(typeColorString);
                            filter = new LightingColorFilter(Color.BLACK, typeColorInt);
                            pokemonImageView2.getBackground().setColorFilter(filter);
                            pokemonImageView2.setVisibility(View.VISIBLE);
                        } else {

                            pokemonImageView2.setImageBitmap(null);
                        }

                        ImageView pokemonImageView3 = (ImageView) findViewById(R.id.partySprite3);
                        if (po[2] != null) {
                            pokemonImageView3.setImageBitmap(po[2].getBitmap());

                            typeColorString = po[2].getTypeColorString(0);
                            typeColorInt = Color.parseColor(typeColorString);
                            filter = new LightingColorFilter(Color.BLACK, typeColorInt);
                            pokemonImageView3.getBackground().setColorFilter(filter);
                            pokemonImageView3.setVisibility(View.VISIBLE);
                        } else {

                            pokemonImageView3.setImageBitmap(null);
                        }

                        ImageView pokemonImageView4 = (ImageView) findViewById(R.id.partySprite4);
                        if (po[3] != null) {
                            pokemonImageView4.setImageBitmap(po[3].getBitmap());

                            typeColorString = po[3].getTypeColorString(0);
                            typeColorInt = Color.parseColor(typeColorString);
                            filter = new LightingColorFilter(Color.BLACK, typeColorInt);
                            pokemonImageView4.getBackground().setColorFilter(filter);
                            pokemonImageView4.setVisibility(View.VISIBLE);
                        } else {

                            pokemonImageView4.setImageBitmap(null);
                        }

                        ImageView pokemonImageView5 = (ImageView) findViewById(R.id.partySprite5);
                        if (po[4] != null) {
                            pokemonImageView5.setImageBitmap(po[4].getBitmap());

                            typeColorString = po[4].getTypeColorString(0);
                            typeColorInt = Color.parseColor(typeColorString);
                            filter = new LightingColorFilter(Color.BLACK, typeColorInt);
                            pokemonImageView5.getBackground().setColorFilter(filter);
                            pokemonImageView5.setVisibility(View.VISIBLE);
                        } else {

                            pokemonImageView5.setImageBitmap(null);
                        }

                        ImageView pokemonImageView6 = (ImageView) findViewById(R.id.partySprite6);
                        if (po[5] != null) {
                            pokemonImageView6.setImageBitmap(po[5].getBitmap());

                            typeColorString = po[5].getTypeColorString(0);
                            typeColorInt = Color.parseColor(typeColorString);
                            filter = new LightingColorFilter(Color.BLACK, typeColorInt);
                            pokemonImageView6.getBackground().setColorFilter(filter);
                            pokemonImageView6.setVisibility(View.VISIBLE);
                        } else {

                            pokemonImageView6.setImageBitmap(null);
                        }
                    }
                });
            }
        }
    }
}
