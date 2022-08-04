package com.amirmohammed.hti22android.ui.names;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.amirmohammed.hti22android.models.MyContact;

@Database(entities = {MyContact.class}, version = 1, exportSchema = false)
public abstract class ContactsDatabase extends RoomDatabase {

    public abstract ContactsDao contactsDao();

    public static ContactsDatabase init(Context context) {
        return Room.databaseBuilder(context,
                ContactsDatabase.class, "contactsDatabase")
                .allowMainThreadQueries()
                .build();
    }


}
