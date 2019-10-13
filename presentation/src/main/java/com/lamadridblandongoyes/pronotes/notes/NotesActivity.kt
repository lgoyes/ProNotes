package com.lamadridblandongoyes.pronotes.notes

import android.widget.Toast
import dagger.android.support.DaggerAppCompatActivity

class NotesActivity: DaggerAppCompatActivity(), NotesContract.View {
    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }
}