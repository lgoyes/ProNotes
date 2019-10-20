package com.lamadridblandongoyes.pronotes.noteedition

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatSpinner
import androidx.appcompat.widget.AppCompatTextView
import com.lamadridblandongoyes.domain.models.Label
import com.lamadridblandongoyes.domain.models.Note
import com.lamadridblandongoyes.pronotes.*
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
    private lateinit var labelSpinner: AppCompatSpinner
    private lateinit var saveButton: AppCompatButton

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_edition)
        bindViews()
        setupSaveButton()
        extractIntentExtras()

        lifecycle.addObserver(presenter)
        presenter.bind(this)
    }

    override fun getFormData(): NoteEditionContract.View.ViewModel =
        NoteEditionContract.View.ViewModel(
            noteTitle.text.toString(),
            noteDescription.text.toString(),
            null,
            labelSpinner.selectedItemPosition
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
        labelSpinner = note_edition_label_picker
    }

    private fun setupSaveButton() {
        this.saveButton.setOnClickListener {
            presenter.onSaveButtonTapped()
        }
    }

    private fun extractIntentExtras() {
        (intent.extras?.getParcelable(INTENT_EXTRA_NOTE) as? Note)?.let{ noteUnderEdition ->
            presenter.setNoteUnderEdition(noteUnderEdition)
        }


        intent.extras?.getParcelableArrayList<Label>(INTENT_EXTRA_LABEL_LIST)?.let {
            presenter.setLabels(it)
        }
    }

    override fun setFormTitle(text: String) {
        formTitle.text = text
    }

    override fun setFormSubtitle(text: String) {
        formSubtitle.text = text
    }

    override fun setNoteTitle(text: String) {
        noteTitle.setText(text)
    }

    override fun setNoteDescription(text: String) {
        noteDescription.setText(text)
    }

    override fun selectSpinnerOption(index: Int) {
        labelSpinner.setSelection(index)
    }

    override fun setSpinnerOptions(labels: List<String>) {
        val arrayAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            labels)

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        this.labelSpinner.adapter = arrayAdapter
    }
}