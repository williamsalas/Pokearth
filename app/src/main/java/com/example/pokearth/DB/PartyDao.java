package com.example.pokearth.DB;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

@Dao
public interface PartyDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long createPokemon(Party party);

    @Query("SELECT * FROM party")
    List<Party> getAllPokemon();

    @Query("DELETE FROM party")
    void deleteAllFromTable();

    @Query("SELECT * FROM party WHERE id IS 0")
    Party getFirstPokemon();

    @Query("SELECT * FROM party WHERE pokemon_number IS 0")
    int getFirstEmpty();

    @Query("SELECT EXISTS(SELECT 1 FROM party WHERE pokemon_number=0 LIMIT 1)")
    int checkEmpty();
}
