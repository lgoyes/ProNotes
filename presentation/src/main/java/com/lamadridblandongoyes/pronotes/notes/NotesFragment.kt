package com.lamadridblandongoyes.pronotes.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lamadridblandongoyes.pronotes.R

class NotesFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_notes, container, false)
    }

    companion object {
        fun newInstance(): NotesFragment {
            return NotesFragment()
        }
    }
}