package com.lamadridblandongoyes.pronotes.noteedition

import com.lamadridblandongoyes.domain.models.Label
import com.lamadridblandongoyes.domain.models.Note
import com.lamadridblandongoyes.pronotes.architecturebasis.BasePresenter
import com.lamadridblandongoyes.pronotes.architecturebasis.BaseView

interface NoteEditionContract {
    interface View: BaseView {
        data class ViewModel(
            val title: String,
            val description: String,
            val reminderDate: String?,
            val labelIndex: Int?
        )
        fun getFormData(): NoteEditionContract.View.ViewModel
        fun navigateBackWith(note: Note?)
        fun showValidationError()
        fun setFormTitle(text: String)
        fun setFormSubtitle(text: String)
        fun setNoteTitle(text: String)
        fun setNoteDescription(text: String)
        fun setSpinnerOptions(labels: List<String>)
        fun selectSpinnerOption(index: Int)
    }

    interface Presenter: BasePresenter<View> {
        fun onSaveButtonTapped()
        fun setNoteUnderEdition(noteUnderEdition: Note?)
        fun setLabels(labels: ArrayList<Label>)
    }
}