package com.example.pokearth.DB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity()
public class Party
{
    @PrimaryKey()
    private int id;

    @ColumnInfo(name = "pokemon_number")
    private int pokemonId;

    public Party()
    {
    }

    public Party (int party_id, int pokemon_id)
    {
        this.id = party_id;
        this.pokemonId = pokemon_id;
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

    @Override
    public String toString()
    {
        return ("Pokemon_Number: " + pokemonId);
    }
}