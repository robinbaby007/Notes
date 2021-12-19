package com.example.notes.DataBase;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.notes.Model.Notes;
import com.example.notes.Dao.NotesDao;

@Database(entities = {Notes.class}, version = 1)
public abstract class NotesDataBase extends RoomDatabase {

    public abstract NotesDao notesDao();

    public static NotesDataBase INSTANCE;

    public static NotesDataBase getDataBaeInstance(Context context) {

            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), NotesDataBase.class, "NotesDataBase").allowMainThreadQueries().build();
            }
            return INSTANCE;

    }


}
