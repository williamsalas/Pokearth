package com.example.pokearth.DB;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PokemonStorageDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long createPokemon(PokemonStorage pokemonStorage);

    @Query("SELECT * FROM pokemonstorage")
    List<Party> getAllPokemon();

    @Query("DELETE FROM pokemonstorage")
    void deleteAllFromTable();
}
