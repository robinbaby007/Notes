package com.example.notes.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.notes.Adapter.NotesListAdapter;
import com.example.notes.InterFace.ItemClickListener;
import com.example.notes.Model.Notes;
import com.example.notes.R;
import com.example.notes.ViewModel.NotesViewModel;
import com.example.notes.databinding.ActivityMainBinding;
import com.example.notes.databinding.DialogAboutMeBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NotesViewModel notesViewModel;
    private List<Notes> notesList;
    private NotesListAdapter notesListAdapter;
    private Dialog aboutMeDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        initAboutMeDialog();
        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);
        notesListAdapter = new NotesListAdapter(MainActivity.this, notesList);
        binding.idRvListNotes.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        binding.idRvListNotes.setAdapter(notesListAdapter);
        binding.idFabAddNotes.setOnClickListener(l -> {
            startActivity(new Intent(MainActivity.this, InsertNotesActivity.class));
        });
        notesViewModel.getNoteList.observe(this, list -> {
            notesList = list;
            notesListAdapter.setList(notesList);
            notesListAdapter.notifyDataSetChanged();
            checkDataCount();
        });
        notesListAdapter.setItemClickListener((v, p) -> {
            if (v.getId() == R.id.id_constraint_layout_notes_resource) {
                Intent intent = new Intent(MainActivity.this, UpdateNotesActivity.class);
                intent.putExtra("id", notesList.get(p).id);
                intent.putExtra("title", notesList.get(p).notesTittle);
                intent.putExtra("subtitle", notesList.get(p).notesSubTittle);
                intent.putExtra("note", notesList.get(p).notes);
                intent.putExtra("priority", notesList.get(p).notesPriority);
                startActivity(intent);
            }
        });

    }

    private void initAboutMeDialog() {
        aboutMeDialog=new Dialog(MainActivity.this);
        DialogAboutMeBinding dlgBinding=DialogAboutMeBinding.inflate(getLayoutInflater());
        aboutMeDialog.setContentView(dlgBinding.getRoot());

    }


    private void checkDataCount() {
        if (notesList.size()>0)
            binding.idImageViewNoData.setVisibility(View.GONE);
        else
            binding.idImageViewNoData.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.about)
            aboutMeDialog.show();
        return super.onOptionsItemSelected(item);
    }
}