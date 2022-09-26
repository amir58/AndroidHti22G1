package com.amirmohammed.hti22android.ui.names.db;

import android.app.Application;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.amirmohammed.hti22android.ui.names.model.MyContact;

@TypeConverters({RoomConverters.class})
@Database(entities = {MyContact.class}, version = 2, exportSchema = false)
public abstract class ContactsDatabase extends RoomDatabase {

    public abstract ContactsDao contactsDao();

    public static ContactsDatabase init( Context context) {
        return Room.databaseBuilder(context,
                ContactsDatabase.class, "contactsDatabase")
                .allowMainThreadQueries()
                .build();
    }

}
