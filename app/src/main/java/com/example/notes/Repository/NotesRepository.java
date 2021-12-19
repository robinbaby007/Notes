package com.example.notes.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.notes.Dao.NotesDao;
import com.example.notes.DataBase.NotesDataBase;
import com.example.notes.Model.Notes;

import java.util.List;

public class NotesRepository {


    public LiveData<List<Notes>> getAllNotesList;
    public NotesDao notesDao;

    public NotesRepository(Application application) {
        NotesDataBase notesDataBase = NotesDataBase.getDataBaeInstance(application);
        notesDao = notesDataBase.notesDao();
        getAllNotesList = notesDao.getAllNotes();

    }

    public void insetNote(Notes notes) {
        notesDao.insertNotes(notes);
    }

    public void deleteNote(int id) {
        notesDao.deleteNotes(id);
    }

    public void updateNotes(Notes notes) {
        notesDao.updateNotes(notes);
    }


}
