package com.lamadridblandongoyes.pronotes.notes

import android.os.Bundle
import android.widget.Toast
import com.lamadridblandongoyes.pronotes.R
import dagger.android.support.DaggerAppCompatActivity

class NotesActivity: DaggerAppCompatActivity(), NotesContract.View {
    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)
    }
}