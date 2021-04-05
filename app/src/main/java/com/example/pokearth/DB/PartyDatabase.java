package com.example.pokearth.DB;

import android.content.*;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Party.class, version = 1)
public abstract class PartyDatabase extends RoomDatabase
{
    public static final String DATABASE_NAME = "saved_party";
    private static PartyDatabase INSTANCE = null;

    public static PartyDatabase getInstance(Context context)
    {
        if (INSTANCE == null)
        {
            synchronized (PartyDatabase.class)
            {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        PartyDatabase.class,
                        DATABASE_NAME)
                        .build();
                Log.d("PartyDatabase", "DB created");
            }
        }
        return INSTANCE;
    }

    public abstract PartyDao partyDao();
}