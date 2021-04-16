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

    @Query("SELECT * FROM party WHERE id IS 1")
    Party getFirstPokemon();
}
