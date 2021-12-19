package com.example.notes.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes.InterFace.ItemClickListener;
import com.example.notes.Model.Notes;
import com.example.notes.R;
import com.example.notes.databinding.NoteListResourceBinding;

import java.util.List;


public class NotesListAdapter extends RecyclerView.Adapter<NotesListAdapter.NotesListViewHolder> {
    private final Context context;
    private List<Notes> notesList;
    private ItemClickListener itemClickListener;

    public NotesListAdapter(Context context, List<Notes> notesList) {
        this.context = context;
        this.notesList = notesList;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public NotesListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NoteListResourceBinding binding = NoteListResourceBinding.inflate(LayoutInflater.from(context), parent, false);
        return new NotesListViewHolder(binding);
    }

    public void setList(List<Notes> notesList) {
        this.notesList = notesList;
    }

    @Override
    public void onBindViewHolder(@NonNull NotesListViewHolder holder, int position) {
        Notes notes = notesList.get(position);
        holder.binding.idTextViewTitle.setText(notes.notesTittle);
        holder.binding.idTextViewSubTitle.setText(notes.notesSubTittle);
        holder.binding.idTextViewDate.setText(notes.notesDate);
        if (notes.notesPriority != null) {
            switch (notes.notesPriority) {
                case "1":
                    holder.binding.idImageViewPriority.setImageResource(R.drawable.round_green_image_view);
                    break;
                case "2":
                    holder.binding.idImageViewPriority.setImageResource(R.drawable.round_yellow_image_view);
                    break;
                case "3":
                    holder.binding.idImageViewPriority.setImageResource(R.drawable.round_red_image_view);
                    break;
            }
        }
        for (Notes name : notesList)
            Log.e("datassss", name.notesTittle);

    }

    @Override
    public int getItemCount() {
        if (notesList == null)
            return 0;
        else
            return notesList.size();
    }

    public class NotesListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private NoteListResourceBinding binding;
        public NotesListViewHolder(@NonNull NoteListResourceBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.idConstraintLayoutNotesResource.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null) {
                itemClickListener.setClick(view, getAdapterPosition());
            }
        }
    }
}