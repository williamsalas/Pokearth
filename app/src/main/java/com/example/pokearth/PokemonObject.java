package com.example.pokearth;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;
import java.net.URL;

import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;
import me.sargunvohra.lib.pokekotlin.model.Pokemon;
import me.sargunvohra.lib.pokekotlin.model.PokemonSpecies;

public class PokemonObject {

    /*
     private String pokemonName;
     private String pokemonSprite;

    public PokemonObject(String name, String sprite)
    {
        this.pokemonName = name;
        this.pokemonSprite = sprite;
    }

    public String getPokemonName() {
        return pokemonName;
    }

    public void setPokemonName(String pokemonName) {
        this.pokemonName = pokemonName;
    }

    public String getPokemonSprite() {
        return pokemonSprite;
    }

    public void setPokemonSprite(String pokemonSprite) {
        this.pokemonSprite = pokemonSprite;
    }
     */

    private Pokemon myPoke;
    private PokemonSpecies myPokeSpecies;
    private Bitmap bitmap;
    PokeApi pokeApi = new PokeApiClient();

    public PokemonObject(int id){
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
}
