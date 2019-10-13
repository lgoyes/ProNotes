package com.lamadridblandongoyes.pronotes.notes

import android.os.Bundle
import android.widget.Toast
import com.lamadridblandongoyes.pronotes.R
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class NotesActivity: DaggerAppCompatActivity(), NotesContract.View {

    @Inject
    lateinit var presenter: NotesContract.Presenter

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)
        lifecycle.addObserver(presenter)
        presenter.bind(this)
    }
}