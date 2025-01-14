package com.example.pokearth.DB;

import android.content.Context;
import android.util.Log;

import java.util.List;

public class PokemonStorageDataSource {
    private static final String TAG = PokemonStorageDataSource.class.getSimpleName();
    private final PokemonStorageDao pokemonStorageDao;

    public PokemonStorageDataSource ( Context context)
    {
        PokemonStorageDatabase database = PokemonStorageDatabase.getInstance(context);
        pokemonStorageDao = database.pokemonStorageDao();
    }

    public void createPokemon(PokemonStorage pokemon)
    {
        long rowID = pokemonStorageDao.createPokemon(pokemon);

        Log.d(TAG, "createPokemon: rowID "+ rowID);
    }

    public List<PokemonStorage> getAllPokemon()
    {
        return pokemonStorageDao.getAllPokemon();
    }

    public void deleteAllFromTable()
    {
        pokemonStorageDao.deleteAllFromTable();
    }

    public PokemonStorage getAt(int storageSlot) {return pokemonStorageDao.getAt(storageSlot);}
}