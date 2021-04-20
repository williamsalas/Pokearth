package com.example.pokearth.DB;

import android.util.Log;
import android.content.Context;
import android.util.Log;
import java.util.List;

public class PartyDataSource
{
    private static final String TAG = PartyDataSource.class.getSimpleName();
    private final PartyDao partyDao;

    public PartyDataSource ( Context context)
    {
        PartyDatabase database = PartyDatabase.getInstance(context);
        partyDao = database.partyDao();
    }

    public void createPokemon(Party pokemon)
    {
        long rowID = partyDao.createPokemon(pokemon);

        Log.d(TAG, "createPokemon: rowID "+ rowID);
    }

    public List<Party> getAllPokemon()
    {
        return partyDao.getAllPokemon();
    }

    public void deleteAllFromTable()
    {
        partyDao.deleteAllFromTable();
    }
}
