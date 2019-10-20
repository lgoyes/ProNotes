package com.lamadridblandongoyes.pronotes.noteedition

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import com.lamadridblandongoyes.domain.models.Note
import com.lamadridblandongoyes.pronotes.INTENT_EXTRA_NOTE
import com.lamadridblandongoyes.pronotes.NOTE_VALIDATION_ERROR_SUBTITLE
import com.lamadridblandongoyes.pronotes.NOTE_VALIDATION_ERROR_TITLE
import com.lamadridblandongoyes.pronotes.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_note_edition.*
import javax.inject.Inject

class NoteEditionActivity: DaggerAppCompatActivity(), NoteEditionContract.View {

    @Inject
    lateinit var presenter: NoteEditionContract.Presenter

    private lateinit var formTitle: AppCompatTextView
    private lateinit var formSubtitle: AppCompatTextView
    private lateinit var noteTitle: AppCompatEditText
    private lateinit var noteDescription: AppCompatEditText
    private lateinit var saveButton: AppCompatButton

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_edition)
        bindViews()
        setupSaveButton()
        fillForm()

        lifecycle.addObserver(presenter)
        presenter.bind(this)
    }

    override fun getFormData(): NoteEditionContract.View.ViewModel =
        NoteEditionContract.View.ViewModel(
            noteTitle.text.toString(),
            noteDescription.text.toString(),
            null,
            null
        )

    override fun navigateBackWith(note: Note?) {
        note?.let {
            val resultIntent = Intent()
            resultIntent.putExtra(INTENT_EXTRA_NOTE,note)
            setResult(Activity.RESULT_OK, resultIntent)
        }
        finish()
    }

    override fun showValidationError() {
        val builder = AlertDialog.Builder(this)
            .apply {
                setTitle(NOTE_VALIDATION_ERROR_TITLE)
                setMessage(NOTE_VALIDATION_ERROR_SUBTITLE)
                setPositiveButton(android.R.string.ok, { _, _ -> })
            }
        builder.show()
    }

    private fun bindViews() {
        formTitle = note_edition_form_title
        formSubtitle = note_edition_form_subtitle
        noteTitle = note_edition_note_title_field
        noteDescription = note_edition_note_description_field
        saveButton = note_edition_save_button
    }

    private fun setupSaveButton() {
        this.saveButton.setOnClickListener {
            presenter.onSaveButtonTapped()
        }
    }

    private fun fillForm() {
        val noteUnderEdition: Note? = intent.extras?.getParcelable(INTENT_EXTRA_NOTE)

        if (noteUnderEdition == null) {
            fillFormForNewNote()
            return
        }

        noteUnderEdition.let {
            presenter.setNoteUnderEdition(noteUnderEdition)
            fillFormForEditingNoteWith(it)
        }
    }

    private fun fillFormForNewNote() {
        formTitle.setText( R.string.new_note)
        formSubtitle.setText(R.string.new_note_subtitle)
    }

    private fun fillFormForEditingNoteWith(note: Note) {
        formTitle.setText( R.string.edit_note )
        formSubtitle.setText( R.string.edit_note_subtitle )
        noteTitle.setText(note.title)
        noteDescription.setText(note.description)
    }
}