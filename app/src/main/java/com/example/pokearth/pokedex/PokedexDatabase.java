package com.example.pokearth.pokedex;

import android.content.Context;
import android.util.Log;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Pokedex.class, version = 1)
public abstract class PokedexDatabase extends RoomDatabase
{
    public static final String DATABASE_NAME = "saved_pokedex";
    private static PokedexDatabase INSTANCE = null;

    public static PokedexDatabase getInstance(Context context)
    {
        if (INSTANCE == null)
        {
            synchronized (PokedexDatabase.class)
            {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        PokedexDatabase.class,
                        DATABASE_NAME)
                        .build();
                Log.d("PokedexDatabase", "DB created");
            }
        }
        return INSTANCE;
    }

    public abstract PokedexDao pokedexDao();
}
