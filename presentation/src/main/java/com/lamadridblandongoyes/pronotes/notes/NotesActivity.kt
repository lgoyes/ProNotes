package com.lamadridblandongoyes.pronotes.notes

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.lamadridblandongoyes.domain.models.Note
import com.lamadridblandongoyes.pronotes.ADDING_NOTE_REQUEST_CODE
import com.lamadridblandongoyes.pronotes.INTENT_EXTRA_NOTE
import com.lamadridblandongoyes.pronotes.NOTES_NUMBER_OF_COLUMNS
import com.lamadridblandongoyes.pronotes.R
import com.lamadridblandongoyes.pronotes.noteedition.NoteEditionActivity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_notes.*
import javax.inject.Inject

class NotesActivity: DaggerAppCompatActivity(), NotesContract.View {

    @Inject
    lateinit var presenter: NotesContract.Presenter

    private var notesAdapter: NotesAdapter = NotesAdapter()

    private lateinit var recyclerView: RecyclerView

    private lateinit var addButton: FloatingActionButton

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)
        bindViews()
        setupRecyclerView()
        setupAddButton()

        lifecycle.addObserver(presenter)
        presenter.bind(this)
    }

    private fun bindViews() {
        this.recyclerView = notes_recyclerview
        this.addButton = notes_floating_action_button
    }

    private fun setupRecyclerView() {
        this.recyclerView.layoutManager = StaggeredGridLayoutManager(
            NOTES_NUMBER_OF_COLUMNS,
            StaggeredGridLayoutManager.VERTICAL)

        this.recyclerView.adapter = notesAdapter
    }

    private fun setupAddButton() {
        this.addButton.setOnClickListener {
            presenter.onAddButtonTapped()
        }
    }

    override fun updateAdapterWith(notes: ArrayList<Note>) {
        this.notesAdapter.clearNotes()
        this.notesAdapter.addNotes(notes)
        this.notesAdapter.notifyDataSetChanged()
    }

    override fun navigateTowardsNoteEditionWith(note: Note?){
        val intent = Intent(this, NoteEditionActivity::class.java)
        note?.let {
            intent.putExtra(INTENT_EXTRA_NOTE, note)
        }
        startActivityForResult(intent, ADDING_NOTE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != Activity.RESULT_OK) {
            return
        }

        when (requestCode) {
            ADDING_NOTE_REQUEST_CODE -> {
                presenter.processAddingNoteResult(data?.extras?.getParcelable(INTENT_EXTRA_NOTE))
            }
        }
    }


}