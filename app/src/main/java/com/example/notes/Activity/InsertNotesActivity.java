package com.example.notes.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.loader.content.AsyncTaskLoader;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.PrecomputedText;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.notes.Dao.NotesDao;
import com.example.notes.DataBase.NotesDataBase;
import com.example.notes.Model.Notes;
import com.example.notes.R;
import com.example.notes.ViewModel.NotesViewModel;
import com.example.notes.databinding.ActivityInsertNotesBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.xml.transform.Result;

public class InsertNotesActivity extends AppCompatActivity {

    private ActivityInsertNotesBinding binding;
    private String title, subtitle, notes;
    private NotesViewModel notesViewModel;
    private String priority = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);
        binding.idFloatingActionButtonSave.setOnClickListener(l -> {

            title = binding.idEditTextTitle.getText().toString();
            subtitle = binding.idEditTextSubTitle.getText().toString();
            notes = binding.idEditTextNotes.getText().toString();
            createNotes(title, subtitle, notes);
        });
        binding.idImageViewGreen.setOnClickListener(l -> {
            priority = "1";
            resetPriorityColor(binding.idImageViewGreen);
        });
        binding.idImageViewYellow.setOnClickListener(l -> {
            priority = "2";
            resetPriorityColor(binding.idImageViewYellow);
        });
        binding.idImageViewRed.setOnClickListener(l -> {
            priority = "3";
            resetPriorityColor(binding.idImageViewRed);
        });
    }

    private void createNotes(String title, String subtitle, String notes) {
        Date calendar = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
        Notes notesObject = new Notes();
        notesObject.notesTittle = title;
        notesObject.notesSubTittle = subtitle;
        notesObject.notes = notes;
        notesObject.notesPriority = priority;
        notesObject.notesDate = dateFormat.format(calendar).toString();
        notesViewModel.insertNotes(notesObject);
        Toast.makeText(InsertNotesActivity.this, "Notes Saved", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void resetPriorityColor(ImageView view) {
        binding.idImageViewGreen.setImageResource(0);
        binding.idImageViewRed.setImageResource(0);
        binding.idImageViewYellow.setImageResource(0);
        view.setImageResource(R.drawable.ic_tick);
    }

}