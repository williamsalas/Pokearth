package com.example.android.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;
import java.net.URL;

import me.sargunvohra.lib.pokekotlin.model.Pokemon;
import me.sargunvohra.lib.pokekotlin.model.PokemonSpecies;

public class PokeObject extends MainActivity {

    final Pokemon[] myPoke = {null};
    final PokemonSpecies[] myPokeSpecies = {null};
    final Bitmap[] bitmap = {null};
    final private Boolean[] isShiny = {null};
    final Health health = new Health();

    public PokeObject(int id) {
        this.myPoke[0] = pokeApi.getPokemon(id);
        this.myPokeSpecies[0] = pokeApi.getPokemonSpecies(id);
        this.isShiny[0] = Math.random() <= 0.25;
        String frontSpriteURL;


        if (this.isShiny[0])
            frontSpriteURL = this.myPoke[0].getSprites().getFrontShiny();
        else
            frontSpriteURL = this.myPoke[0].getSprites().getFrontDefault();
        try {
            this.bitmap[0] = BitmapFactory.decodeStream((InputStream) new URL(frontSpriteURL).getContent()); // networking
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return this.myPokeSpecies[0].getId();
    }

    public Bitmap getBitmap() {
        return this.bitmap[0];
    }

    public boolean getShinyStatus() {
        return this.isShiny[0];
    }

    public String getName() {
        return this.myPokeSpecies[0].getName();
    }

    public int getCaptureRate() {
        return this.myPokeSpecies[0].getCaptureRate();
    }
}
