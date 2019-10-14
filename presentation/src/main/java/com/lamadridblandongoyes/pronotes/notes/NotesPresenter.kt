package com.lamadridblandongoyes.pronotes.notes

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.lamadridblandongoyes.domain.interactors.base.FlowableUseCase
import com.lamadridblandongoyes.domain.models.Note
import com.lamadridblandongoyes.pronotes.architecturebasis.BasePresenter
import com.lamadridblandongoyes.pronotes.architecturebasis.IErrorHandler
import io.reactivex.disposables.CompositeDisposable

class NotesPresenter(
    private val getAllNotesInteractor: FlowableUseCase<List<Note>, Unit>
): BasePresenter<NotesContract.View>, NotesContract.Presenter {
    override var view: NotesContract.View? = null
    override var errorHandler: IErrorHandler? = null
    override val subscriptions = CompositeDisposable()

    private var notes: ArrayList<Note> = ArrayList<Note>()

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun start() {
        fetchNotes()
    }

    private fun fetchNotes() {
        notes.add(
            Note(
                0,
                1,
                "Title note 1",
                "Description note 1",
                null)
        )

        notes.add(
            Note(
                0,
                1,
                "Title note 2",
                "Description note 2",
                null)
        )

        view?.let {
            it.updateAdapterWith(notes)
        }
    }
}