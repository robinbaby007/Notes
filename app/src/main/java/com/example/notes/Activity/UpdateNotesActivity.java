package com.example.notes.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.notes.Model.Notes;
import com.example.notes.R;
import com.example.notes.ViewModel.NotesViewModel;
import com.example.notes.databinding.ActivityUpdateNotesBinding;
import com.example.notes.databinding.BottomSheetDeleteBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class UpdateNotesActivity extends AppCompatActivity {

    private ActivityUpdateNotesBinding binding;
    private String note, title, subtitle, priority;
    private int noteId;
    private NotesViewModel notesViewModel;
    private BottomSheetDialog bottomSheetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        noteId = getIntent().getIntExtra("id",0);
        note = getIntent().getStringExtra("note");
        title = getIntent().getStringExtra("title");
        subtitle = getIntent().getStringExtra("subtitle");
        priority = getIntent().getStringExtra("priority");
        notesViewModel=new ViewModelProvider(this).get(NotesViewModel.class);
        setPriority();
        initBottomSheet();

        binding.idEditTextTitle.setText(title);
        binding.idEditTextSubTitle.setText(subtitle);
        binding.idEditTextNotes.setText(note);
        binding.idFloatingActionButtonSave.setOnClickListener(l -> {
            updateNotes();
        });


    }

    private void initBottomSheet() {
        bottomSheetDialog=new BottomSheetDialog(UpdateNotesActivity.this);
         BottomSheetDeleteBinding binding=BottomSheetDeleteBinding.inflate(getLayoutInflater());
         bottomSheetDialog.setContentView(binding.getRoot());
        binding.idTextViewBottomSheetYes.setOnClickListener(l->{
            Toast.makeText(UpdateNotesActivity.this, "Note Deleted", Toast.LENGTH_SHORT).show();
            bottomSheetDialog.dismiss();
            deleteNote();
        });
        binding.idTextViewBottomSheetNo.setOnClickListener(l->{
            bottomSheetDialog.dismiss();
        });
    }
    private void deleteNote(){
        notesViewModel.deleteNotes(noteId);
        finish();
    }

    private void updateNotes() {
        title= binding.idEditTextTitle.getText().toString();
        subtitle= binding.idEditTextSubTitle.getText().toString();
        note= binding.idEditTextNotes.getText().toString();
        Date calendar = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
        Notes notesObject = new Notes();
        notesObject.id= noteId;
        notesObject.notesTittle = title;
        notesObject.notesSubTittle = subtitle;
        notesObject.notes = note;
        notesObject.notesPriority = priority;
        notesObject.notesDate = dateFormat.format(calendar).toString();
        notesViewModel.updateNote(notesObject);
        Toast.makeText(UpdateNotesActivity.this, "Notes Updated", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void resetPriorityColor(ImageView view) {
        binding.idImageViewGreen.setImageResource(0);
        binding.idImageViewRed.setImageResource(0);
        binding.idImageViewYellow.setImageResource(0);
        view.setImageResource(R.drawable.ic_tick);
    }

    private void setPriority() {
        switch (priority) {
            case "1":
                binding.idImageViewGreen.setImageResource(R.drawable.ic_tick);
                break;
            case "2":
                binding.idImageViewYellow.setImageResource(R.drawable.ic_tick);
                break;
            case "3":
                binding.idImageViewRed.setImageResource(R.drawable.ic_tick);
                break;
        }

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.id_button_menu_delete)
              bottomSheetDialog.show();
        return super.onOptionsItemSelected(item);
    }
}