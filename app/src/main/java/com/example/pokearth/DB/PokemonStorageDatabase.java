package com.example.pokearth.DB;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = PokemonStorage.class, version = 1)
public abstract class PokemonStorageDatabase extends RoomDatabase
{
    public static final String DATABASE_NAME = "saved_storage";
    private static PokemonStorageDatabase INSTANCE = null;

    public static PokemonStorageDatabase getInstance(Context context)
    {
        if (INSTANCE == null)
        {
            synchronized (PokemonStorageDatabase.class)
            {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        PokemonStorageDatabase.class,
                        DATABASE_NAME)
                        .build();
                Log.d("PokemonStorageDatabase", "DB created");
            }
        }
        return INSTANCE;
    }

    public abstract PokemonStorageDao pokemonStorageDao();
}
