package com.lamadridblandongoyes.pronotes.noteedition

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
    }

    interface Presenter: BasePresenter<View> {
        fun onSaveButtonTapped()
    }
}