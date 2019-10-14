package com.lamadridblandongoyes.pronotes.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lamadridblandongoyes.domain.models.Note
import com.lamadridblandongoyes.pronotes.R
import com.lamadridblandongoyes.pronotes.notes.NotesAdapter.NoteViewHolder
import kotlinx.android.synthetic.main.row_note.view.*

class NotesAdapter: RecyclerView.Adapter<NoteViewHolder>() {

    private var notes: ArrayList<Note> = ArrayList<Note>()

    fun addNotes(notes: ArrayList<Note>) {
        this.notes.addAll(notes)
    }

    fun clearNotes() {
        this.notes.clear()
    }

    fun addNote(note: Note) {
        this.notes.add(note)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_note,parent,false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.tvTitle.text = notes[position].title
        holder.tvDescription.text = notes[position].description
    }

    inner class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.note_title
        val tvDescription: TextView = itemView.note_description
    }
}