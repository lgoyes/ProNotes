package com.lamadridblandongoyes.pronotes.notes

import com.lamadridblandongoyes.domain.models.Note
import com.lamadridblandongoyes.pronotes.architecturebasis.BasePresenter
import com.lamadridblandongoyes.pronotes.architecturebasis.BaseView

interface NotesContract {
    interface View: BaseView {
        fun updateAdapterWith(notes: ArrayList<Note>)
        fun navigateTowardsNoteEditionWith(note: Note?)
        fun askForDeletionConfirmationWith(index: Int)
    }

    interface Presenter: BasePresenter<View> {
        fun onAddButtonTapped()
        fun processAddingNoteResult(note: Note?)
        fun processEditingNoteResult(note: Note?)
        fun processItemTappedWith(index: Int)
        fun processItemLongTappedWith(index: Int)
        fun deletionConfirmedWith(index: Int)
    }
}