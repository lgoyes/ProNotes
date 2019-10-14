package com.lamadridblandongoyes.pronotes.noteedition

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
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

        lifecycle.addObserver(presenter)
        presenter.bind(this)
    }

    private fun bindViews() {
        formTitle = note_edition_form_title
        formSubtitle = note_edition_form_subtitle
        noteTitle = note_edition_note_title_field
        noteDescription = note_edition_note_description_field
        saveButton = note_edition_save_button
    }
}