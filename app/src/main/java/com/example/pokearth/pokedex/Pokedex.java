package com.example.pokearth.pokedex;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity()

public class Pokedex
{
    @PrimaryKey()
    private int id;

    @ColumnInfo(name = "pokemon_number")
    private int pokemonId;

    //value will be 0 or 1
    @ColumnInfo(name = "isSeen")
    private int isSeen;

    @ColumnInfo(name = "pokeObject")
    private String pokeObject;

    @ColumnInfo(name = "pokeSpecies")
    private String pokeSpecies;

    @ColumnInfo(name = "bitmap_string")
    private byte[] bitmapString;

    public Pokedex()
    {
    }

    public Pokedex(int party_id, int pokemon_id, int isSeen, String pokeObject, String pokeSpecies, byte[] bmapString)
    {
        this.id = party_id;
        this.pokemonId = pokemon_id;
        this.isSeen = isSeen;
        this.bitmapString = bmapString;
        this.pokeObject = pokeObject;
        this.pokeSpecies = pokeSpecies;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getPokemonId()
    {
        return pokemonId;
    }

    public void setPokemonId(int pokemon_id)
    {
        this.pokemonId = pokemon_id;
    }

    public void setIsSeen(int isSeen) {
        this.isSeen = isSeen;
    }

    public int getIsSeen() {
        return isSeen;
    }

    public byte[] getBitmapString() {
        return bitmapString;
    }

    public void setBitmapString(byte[] bitmapString) {
        this.bitmapString = bitmapString;
    }

    public String getPokeObject() {
        return pokeObject;
    }

    public void setPokeObject(String pokeObject) {
        this.pokeObject = pokeObject;
    }

    public String getPokeSpecies() {
        return pokeSpecies;
    }

    public void setPokeSpecies(String pokeSpecies) {
        this.pokeSpecies = pokeSpecies;
    }

    @Override
    public String toString()
    {
        return ("Pokemon_Number: " + pokemonId);
    }
}
