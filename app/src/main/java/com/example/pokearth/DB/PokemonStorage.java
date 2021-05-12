package com.example.pokearth.DB;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PokemonStorage
{
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "pokemon_number")
    private int pokemonId;

    @ColumnInfo(name = "pokeObject")
    private String pokeObject;

    @ColumnInfo(name = "pokeSpecies")
    private String pokeSpecies;

    @ColumnInfo(name = "bitmap_string")
    private byte[] bitmapString;


    public PokemonStorage()
    {
    }

    public PokemonStorage (int pokemon_id)
    {
        this.pokemonId = pokemon_id;
    }

    public PokemonStorage (int id, int pokemon_id, String pokeObject, String pokeSpecies, byte[] bmapString)
    {
        this.id = id;
        this.pokemonId = pokemon_id;
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

    public String getPokeObject() {return pokeObject;}

    public void setPokeObject(String pokeObject) {
        this.pokeObject = pokeObject;
    }

    public String getPokeSpecies() {
        return pokeSpecies;
    }

    public void setPokeSpecies(String pokeSpecies) {
        this.pokeSpecies = pokeSpecies;
    }

    public byte[] getBitmapString() {
        return bitmapString;
    }

    public void setBitmapString(byte[] bitmapString) {
        this.bitmapString = bitmapString;
    }
}
