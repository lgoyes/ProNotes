package com.lamadridblandongoyes.pronotes.noteedition

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.lamadridblandongoyes.domain.models.Label
import com.lamadridblandongoyes.domain.models.Note
import com.lamadridblandongoyes.pronotes.EDIT_NOTE_SUBTITLE
import com.lamadridblandongoyes.pronotes.EDIT_NOTE_TITLE
import com.lamadridblandongoyes.pronotes.NEW_NOTE_SUBTITLE
import com.lamadridblandongoyes.pronotes.NEW_NOTE_TITLE
import com.lamadridblandongoyes.pronotes.architecturebasis.BasePresenter
import com.lamadridblandongoyes.pronotes.architecturebasis.IErrorHandler
import io.reactivex.disposables.CompositeDisposable

class NoteEditionPresenter: BasePresenter<NoteEditionContract.View>, NoteEditionContract.Presenter {

    override var view: NoteEditionContract.View? = null
    override var errorHandler: IErrorHandler? = null
    override val subscriptions = CompositeDisposable()

    private var noteUnderEdition: Note? = null
    private var labels: ArrayList<Label>? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun start() {
        labels?.let {
            view?.setSpinnerOptions(it.map { it.title })
        }

        if (noteUnderEdition == null) {
            fillFormForNewNote()
        } else {
            fillFormForEditingNote()
        }
    }

    fun fillFormForNewNote() {
        view?.setFormTitle(NEW_NOTE_TITLE)
        view?.setFormSubtitle(NEW_NOTE_SUBTITLE)
    }

    fun fillFormForEditingNote(){
        this.noteUnderEdition?.let { noteUnderEdition ->
            view?.setFormTitle(EDIT_NOTE_TITLE)
            view?.setFormSubtitle(EDIT_NOTE_SUBTITLE)
            view?.setNoteTitle(noteUnderEdition.title)
            view?.setNoteDescription(noteUnderEdition.description)

            labels
                ?.indexOfFirst { it.labelId == noteUnderEdition.labelId }
                ?.let {
                    view?.selectSpinnerOption( it )
                }
        }
    }

    override fun onSaveButtonTapped() {
        view?.getFormData()?.let { data ->
            if(isValid(data)) {
                view?.navigateBackWith(
                    wrapEditedData(data)
                )
            }else{
                view?.showValidationError()
            }
        }
    }

    override fun setLabels(labels: ArrayList<Label>) {
        this.labels = labels
    }

    private fun isValid(data: NoteEditionContract.View.ViewModel): Boolean {
        return !(data.description.isEmpty() && data.title.isEmpty())
    }

    override fun setNoteUnderEdition(noteUnderEdition: Note?) {
        this.noteUnderEdition = noteUnderEdition
    }

    private fun wrapEditedData(viewModel: NoteEditionContract.View.ViewModel): Note {
        return Note(
            noteUnderEdition?.noteId,
            labels!![viewModel.labelIndex ?: 0].labelId,
            viewModel.title,
            viewModel.description,
            viewModel.reminderDate
        )
    }
}