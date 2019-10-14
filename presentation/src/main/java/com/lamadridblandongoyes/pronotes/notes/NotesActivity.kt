package com.lamadridblandongoyes.pronotes.notes

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.lamadridblandongoyes.domain.models.Note
import com.lamadridblandongoyes.pronotes.NOTES_NUMBER_OF_COLUMNS
import com.lamadridblandongoyes.pronotes.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_notes.*
import javax.inject.Inject

class NotesActivity: DaggerAppCompatActivity(), NotesContract.View {

    @Inject
    lateinit var presenter: NotesContract.Presenter

    private var notesAdapter: NotesAdapter = NotesAdapter()
    private lateinit var recyclerView: RecyclerView

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)
        setupRecyclerView()

        lifecycle.addObserver(presenter)
        presenter.bind(this)
    }

    private fun setupRecyclerView() {
        this.recyclerView = notes_recyclerview

        this.recyclerView.layoutManager = StaggeredGridLayoutManager(
            NOTES_NUMBER_OF_COLUMNS,
            StaggeredGridLayoutManager.VERTICAL)

        this.recyclerView.adapter = notesAdapter
    }

    override fun updateAdapterWith(notes: ArrayList<Note>) {
        this.notesAdapter.clearNotes()
        this.notesAdapter.addNotes(notes)
        this.notesAdapter.notifyDataSetChanged()
    }
}