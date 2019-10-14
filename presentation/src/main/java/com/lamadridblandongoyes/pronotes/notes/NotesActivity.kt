package com.lamadridblandongoyes.pronotes.notes

import android.app.Activity
import android.app.AlertDialog
import android.app.Instrumentation
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.lamadridblandongoyes.domain.models.Note
import com.lamadridblandongoyes.pronotes.*
import com.lamadridblandongoyes.pronotes.noteedition.NoteEditionActivity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_notes.*
import javax.inject.Inject

class NotesActivity: DaggerAppCompatActivity(), NotesContract.View, NotesAdapter.ItemTapListener {

    @Inject
    lateinit var presenter: NotesContract.Presenter

    private var notesAdapter: NotesAdapter = NotesAdapter(this)

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

    override fun onItemTapped(index: Int) {
        presenter.processItemTappedWith(index= index)
    }

    override fun onItemLongTapped(index: Int): Boolean {
        presenter.processItemLongTappedWith(index = index)
        return true
    }

    override fun askForDeletionConfirmationWith(index: Int) {
        val builder = AlertDialog.Builder(this)
            .apply {
                setTitle(REMOVE_NOTE_TITLE)
                setMessage(REMOVE_NOTE_SUBTITLE)
                setPositiveButton(android.R.string.yes) { dialog, which ->
                    presenter.deletionConfirmedWith(index)
                }
                setNegativeButton(android.R.string.no) { dialog, which -> }
            }
        builder.show()
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

        if (note == null) {
            startActivityForResult(intent, ADDING_NOTE_REQUEST_CODE)
            return
        }

        note.let {
            intent.putExtra(INTENT_EXTRA_NOTE, note)
            startActivityForResult(intent, EDITING_NOTE_REQUEST_CODE)
        }
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
            EDITING_NOTE_REQUEST_CODE -> {
                presenter.processEditingNoteResult(data?.extras?.getParcelable(INTENT_EXTRA_NOTE))
            }
        }
    }


}