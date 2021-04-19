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

    public PokemonStorage()
    {
    }

    public PokemonStorage (int pokemon_id)
    {
        this.pokemonId = pokemon_id;
    }

    public PokemonStorage (int id, int pokemon_id)
    {
        this.id = id;
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
}
