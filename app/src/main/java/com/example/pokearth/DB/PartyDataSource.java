package com.example.pokearth.DB;

import android.util.Log;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class PartyDataSource {
    private static final String TAG = PartyDataSource.class.getSimpleName();
    private final PartyDao partyDao;

    public PartyDataSource(Context context) {
        PartyDatabase database = PartyDatabase.getInstance(context);
        partyDao = database.partyDao();
    }

    public void createPokemon(Party pokemon) {
        long rowID = partyDao.createPokemon(pokemon);

        Log.d(TAG, "createPokemon: rowID " + rowID);
    }

    public List<Party> getAllPartyPokemon() {
        return partyDao.getAllPokemon();
    }

    public Party getFirstPokemon() {
        return partyDao.getFirstPokemon();
    }

    public int getFirstEmptySlot() {
        // assert that party is not full when called
        int partyIDToFill = -1;
        int i = 0;
        List<Party> playerPartyPokemon = this.getAllPartyPokemon();
        if (playerPartyPokemon.size() < 6)
            partyIDToFill = playerPartyPokemon.size() + 1;

        while (i < 6 && partyIDToFill == -1)
        {
            Party currentPartyPokemon = playerPartyPokemon.get(i);
            int currentPartyPokemonID = currentPartyPokemon.getPokemonId();

            if (currentPartyPokemonID == 0)
                partyIDToFill = i+1;
            i++;
        }

        for (Party p : playerPartyPokemon)
            Log.d(TAG, "" + p.toString() + " getId: " + p.getId() + "getPokemonID: " + p.getPokemonId());

        return partyIDToFill;
    }

    public void deleteAllFromTable() {
        partyDao.deleteAllFromTable();
    }
}
