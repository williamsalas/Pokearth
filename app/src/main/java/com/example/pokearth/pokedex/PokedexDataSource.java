package com.example.pokearth.pokedex;


import android.content.Context;
import android.util.Log;

import java.util.List;

public class PokedexDataSource
{
    private static final String TAG = PokedexDataSource .class.getSimpleName();
    private final PokedexDao pokedexDao;

    public PokedexDataSource  ( Context context)
    {
        PokedexDatabase database = PokedexDatabase.getInstance(context);
        pokedexDao = database.pokedexDao();
    }

    public void createPokemon(Pokedex pokemon)
    {
        long rowID = pokedexDao.createPokemon(pokemon);

        Log.d(TAG, "createPokemon: rowID "+ rowID);
    }

    public List<Pokedex> getAllPokemon()
    {
        return pokedexDao.getAllPokemon();
    }

    public void deleteAllFromTable()
    {
        pokedexDao.deleteAllFromTable();
    }
}