package com.example.pokearth;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;
import me.sargunvohra.lib.pokekotlin.model.Pokemon;
import me.sargunvohra.lib.pokekotlin.model.PokemonEntry;
import me.sargunvohra.lib.pokekotlin.model.PokemonHeldItem;
import me.sargunvohra.lib.pokekotlin.model.PokemonSprites;
import me.sargunvohra.lib.pokekotlin.model.VersionGroupFlavorText;

public class Item {

    private String name;
    private String spriteURL;
    private Bitmap bitmap;
    private String description;
    private String quantityInBag;
    List<VersionGroupFlavorText> text;

    PokeApi pokeApi = new PokeApiClient();

    public Item(int id) {
        this.name = this.pokeApi.getItem(id).getName();

        String spriteURL = this.pokeApi.getItem(id).getSprites().getDefault();
        text = this.pokeApi.getItem(id).getFlavorTextEntries();
        description = text.get(0).getText();
        quantityInBag = "0";

        try {
            this.bitmap = BitmapFactory.decodeStream((InputStream) new URL(spriteURL).getContent()); // networking
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpriteURL() {
        return spriteURL;
    }

    public void setSpriteURL(String spriteURL) {
        this.spriteURL = spriteURL;
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public String getDescription() { return description;}

    public String getQuantityInBag() { return quantityInBag;}

    public String setQuantityInBag(String newQuantity) { return quantityInBag = newQuantity;}
}