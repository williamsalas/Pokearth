package com.example.pokearth.pokedex;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;
import java.net.URL;

import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;
import me.sargunvohra.lib.pokekotlin.model.Pokemon;
import me.sargunvohra.lib.pokekotlin.model.PokemonSpecies;
import me.sargunvohra.lib.pokekotlin.model.PokemonStat;
import me.sargunvohra.lib.pokekotlin.model.Stat;

public class PokemonPokedexObject {

    private Pokemon myPoke;
    private PokemonSpecies myPokeSpecies;
    private Bitmap bitmap;
    private boolean collected = true;
    PokeApi pokeApi = new PokeApiClient();

    public PokemonPokedexObject(int id){
        this.myPoke = pokeApi.getPokemon(id);
        this.myPokeSpecies = pokeApi.getPokemonSpecies(id);
        String spriteURL;

        spriteURL = this.myPoke.getSprites().getFrontDefault();
        try {
            this.bitmap = BitmapFactory.decodeStream((InputStream) new URL(spriteURL).getContent()); // networking
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return this.myPokeSpecies.getId();
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public String getPokemonName() {
        return this.myPokeSpecies.getName();
    }

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }
}
