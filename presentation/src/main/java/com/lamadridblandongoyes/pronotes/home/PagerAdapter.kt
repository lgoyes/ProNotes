package com.lamadridblandongoyes.pronotes.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.lamadridblandongoyes.pronotes.labels.LabelsFragment
import com.lamadridblandongoyes.pronotes.notes.NotesFragment

class PagerAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                NotesFragment.newInstance()
            }
            else -> {
                LabelsFragment.newInstance()
            }
        }
    }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> {
                "Notes"
            }
            else -> {
                "Labels"
            }
        }
    }
}