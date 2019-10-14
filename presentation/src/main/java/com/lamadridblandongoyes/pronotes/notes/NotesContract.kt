package com.lamadridblandongoyes.pronotes.notes

import com.lamadridblandongoyes.domain.models.Note
import com.lamadridblandongoyes.pronotes.architecturebasis.BasePresenter
import com.lamadridblandongoyes.pronotes.architecturebasis.BaseView

interface NotesContract {
    interface View: BaseView {
        fun updateAdapterWith(notes: ArrayList<Note>)
        fun navigateTowardsNoteEditionWith(note: Note?)
    }

    interface Presenter: BasePresenter<View> {
        fun onAddButtonTapped()
        fun processAddingNoteResult(note: Note?)
    }
}