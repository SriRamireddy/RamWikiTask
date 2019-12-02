package com.ramireddy.ramwikitask.roomdata;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ramireddy.ramwikitask.model.Page;

@Database(entities = {Page.class}, version = 1, exportSchema = false)
public abstract class WikiRoomDataBase extends RoomDatabase
{
    public abstract WikiDAO wikiDAO();
    private static WikiRoomDataBase INSTANCE;

    public static WikiRoomDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WikiRoomDataBase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),WikiRoomDataBase.class,"wikidata")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
