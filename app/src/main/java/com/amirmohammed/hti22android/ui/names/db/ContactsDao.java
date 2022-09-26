package com.amirmohammed.hti22android.ui.names.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.amirmohammed.hti22android.ui.names.model.MyContact;

import java.util.List;

@Dao
public interface ContactsDao {

    @Query("SELECT * FROM MyContact")
    List<MyContact> getContacts();

    @Insert
    long insertContact(MyContact myContact);

    @Update
    void updateContact(MyContact myContact);

    @Delete
    void deleteContact(MyContact myContact);
}
