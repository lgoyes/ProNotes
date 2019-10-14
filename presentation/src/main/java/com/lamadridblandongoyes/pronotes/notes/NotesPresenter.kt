package com.lamadridblandongoyes.pronotes.notes

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.lamadridblandongoyes.domain.interactors.base.FlowableUseCase
import com.lamadridblandongoyes.domain.interactors.base.ObservableUseCase
import com.lamadridblandongoyes.domain.models.Note
import com.lamadridblandongoyes.pronotes.architecturebasis.BasePresenter
import com.lamadridblandongoyes.pronotes.architecturebasis.IErrorHandler
import io.reactivex.disposables.CompositeDisposable

class NotesPresenter(
    private val getAllNotesInteractor: FlowableUseCase<List<Note>, Unit>,
    private val insertNoteInteractor: ObservableUseCase<Long, Note>
): BasePresenter<NotesContract.View>, NotesContract.Presenter {

    override var view: NotesContract.View? = null
    override var errorHandler: IErrorHandler? = null
    override val subscriptions = CompositeDisposable()

    private var notes: ArrayList<Note> = ArrayList<Note>()

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun start() {
        fetchNotes()
    }

    override fun onAddButtonTapped() {
        view?.navigateTowardsNoteEditionWith(note = null)
    }

    override fun processAddingNoteResult(note: Note?) {
        note?.let {
            subscriptions.add(
                this.insertNoteInteractor
                    .execute(it,
                        {
                            notes.add(note)
                            this.updateAdapter()
                        }, { error ->
                            this.handleException(error)
                        })
            )
        }
    }

    private fun fetchNotes() {
        subscriptions.add(
            getAllNotesInteractor.execute(Unit,{ extractedNotes ->
                this.notes = ArrayList( extractedNotes )
                this.updateAdapter()
            },{
                this.handleException(it)
            })
        )
    }

    private fun updateAdapter() {
        view?.updateAdapterWith(this.notes)
    }
}