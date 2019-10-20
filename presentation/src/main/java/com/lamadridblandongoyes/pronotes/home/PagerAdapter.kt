package com.lamadridblandongoyes.pronotes.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.lamadridblandongoyes.pronotes.TAB_TITLE_LABELS
import com.lamadridblandongoyes.pronotes.TAB_TITLE_NOTES
import com.lamadridblandongoyes.pronotes.labels.LabelsFragment
import com.lamadridblandongoyes.pronotes.notes.NotesFragment

class PagerAdapter(
    fragmentManager: FragmentManager,
    private val notesFragment: NotesFragment,
    private val labelsFragment: LabelsFragment
): FragmentPagerAdapter(fragmentManager) {


    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                notesFragment
            }
            else -> {
                labelsFragment
            }
        }
    }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> {
                TAB_TITLE_NOTES
            }
            else -> {
                TAB_TITLE_LABELS
            }
        }
    }
}