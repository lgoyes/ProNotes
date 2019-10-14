package com.lamadridblandongoyes.pronotes.noteedition

import com.lamadridblandongoyes.domain.models.Note
import com.lamadridblandongoyes.pronotes.architecturebasis.BasePresenter
import com.lamadridblandongoyes.pronotes.architecturebasis.IErrorHandler
import io.reactivex.disposables.CompositeDisposable

class NoteEditionPresenter: BasePresenter<NoteEditionContract.View>, NoteEditionContract.Presenter {


    override var view: NoteEditionContract.View? = null
    override var errorHandler: IErrorHandler? = null
    override val subscriptions = CompositeDisposable()

    private var noteUnderEdition: Note? = null

    override fun onSaveButtonTapped() {
        view?.getFormData()?.let { data ->
            view?.navigateBackWith(
                wrapEditedData(data)
            )
        }
    }

    override fun setNoteUnderEdition(noteUnderEdition: Note?) {
        this.noteUnderEdition = noteUnderEdition
    }

    private fun wrapEditedData(viewModel: NoteEditionContract.View.ViewModel): Note {
        return Note(
            noteUnderEdition?.noteId,
            null,
            viewModel.title,
            viewModel.description,
            viewModel.reminderDate
        )
    }
}