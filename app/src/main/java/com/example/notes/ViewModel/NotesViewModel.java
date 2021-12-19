package com.example.notes.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.notes.Model.Notes;
import com.example.notes.Repository.NotesRepository;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    NotesRepository notesRepository;
   public LiveData<List<Notes>> getNoteList;


    public NotesViewModel(@NonNull Application application) {
        super(application);

        notesRepository = new NotesRepository(application);
        getNoteList = notesRepository.getAllNotesList;

    }

    public void insertNotes(Notes notes) {
        notesRepository.insetNote(notes);
    }

    public void updateNote(Notes notes) {
        notesRepository.updateNotes(notes);
    }

    public void deleteNotes(int id) {
        notesRepository.deleteNote(id);
    }

}
