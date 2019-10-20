package com.lamadridblandongoyes.pronotes.labels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lamadridblandongoyes.pronotes.R

class LabelsFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_labels, container, false)
    }

    companion object {
        fun newInstance(): LabelsFragment {
            return LabelsFragment()
        }
    }
}