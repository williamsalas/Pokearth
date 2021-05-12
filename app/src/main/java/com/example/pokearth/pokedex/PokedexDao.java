package com.example.pokearth.pokedex;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PokedexDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long createPokemon(Pokedex pokedex);

    @Query("SELECT * FROM pokedex")
    List<Pokedex> getAllPokemon();

    @Query("DELETE FROM pokedex")
    void deleteAllFromTable();
}
