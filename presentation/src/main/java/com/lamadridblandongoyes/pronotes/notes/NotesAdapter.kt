package com.lamadridblandongoyes.pronotes.notes

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.lamadridblandongoyes.domain.models.Label
import com.lamadridblandongoyes.domain.models.Note
import com.lamadridblandongoyes.pronotes.R
import com.lamadridblandongoyes.pronotes.notes.NotesAdapter.NoteViewHolder
import kotlinx.android.synthetic.main.row_note.view.*

class NotesAdapter(private var itemTapListener: ItemTapListener)
    : RecyclerView.Adapter<NoteViewHolder>() {

    private var notes: ArrayList<Note> = ArrayList<Note>()
    private var labels: ArrayList<Label> = ArrayList<Label>()

    fun addNotes(notes: ArrayList<Note>) {
        this.notes.addAll(notes)
    }

    fun clearNotes() {
        this.notes.clear()
    }

    fun addLabels(labels: List<Label>) {
        this.labels.addAll(labels)
    }

    fun clearLabels() {
        this.labels.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_note,parent,false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]

        holder.tvTitle.text = note.title
        holder.tvDescription.text = note.description

        note.labelId?.let { labelId ->
            labels.firstOrNull { it.labelId == labelId }?.let {
                    holder.containerView.setCardBackgroundColor(Color.parseColor(it.color))
                }
        }

        holder.itemView.setOnClickListener {
            itemTapListener.onItemTapped(position)
        }

        holder.itemView.setOnLongClickListener {
            itemTapListener.onItemLongTapped(position)
        }
    }

    inner class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvTitle: AppCompatTextView = itemView.note_title
        val tvDescription: AppCompatTextView = itemView.note_description
        val containerView: CardView = itemView.row_note_container
    }
}