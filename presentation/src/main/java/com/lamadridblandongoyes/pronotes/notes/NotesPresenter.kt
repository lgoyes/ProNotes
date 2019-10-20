package com.lamadridblandongoyes.pronotes.notes

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.lamadridblandongoyes.domain.interactors.base.FlowableUseCase
import com.lamadridblandongoyes.domain.interactors.base.ObservableUseCase
import com.lamadridblandongoyes.domain.models.Label
import com.lamadridblandongoyes.domain.models.Note
import com.lamadridblandongoyes.pronotes.architecturebasis.BasePresenter
import com.lamadridblandongoyes.pronotes.architecturebasis.IErrorHandler
import io.reactivex.disposables.CompositeDisposable

class NotesPresenter(
    private val getAllNotesInteractor: FlowableUseCase<List<Note>, Unit>,
    private val getAllLabelsInteractor: FlowableUseCase<List<Label>, Unit>,
    private val insertNoteInteractor: ObservableUseCase<Long, Note>,
    private val updateNoteInteractor: ObservableUseCase<Int, Note>,
    private val deleteNoteInteractor: ObservableUseCase<Int, Note>
): BasePresenter<NotesContract.View>, NotesContract.Presenter {

    override var view: NotesContract.View? = null
    override var errorHandler: IErrorHandler? = null
    override val subscriptions = CompositeDisposable()

    private var notes: ArrayList<Note> = ArrayList<Note>()
    private var labels: List<Label>? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun start() {
        fetchLabels()
    }

    override fun processItemTappedWith(index: Int) {
        notes[index].let { note ->
            labels?.let {
                view?.navigateTowardsNoteEditionWith(note = note, labels = it)
            }
        }
    }

    override fun processItemLongTappedWith(index: Int) {
        view?.askForDeletionConfirmationWith(index)
    }

    override fun deletionConfirmedWith(index: Int) {
        notes[index].let { note ->
            subscriptions.add(
                this.deleteNoteInteractor
                    .execute(note,
                        {
                            notes.removeAt(index)
                            this.updateAdapter()
                        }, {
                            this.handleException(it)
                        })
            )
        }
    }

    override fun onAddButtonTapped() {
        labels?.let {

            if (it.isEmpty()) {
                view?.presentEmptyLabelsDialog()
            } else {
                view?.navigateTowardsNoteEditionWith(note = null, labels = it)
            }
        }
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

    override fun processEditingNoteResult(note: Note?) {
        note?.let {
            subscriptions.add(
                this.updateNoteInteractor
                    .execute(it,
                        {
                            notes.indexOfFirst {
                                it.noteId == note.noteId
                            }.let { indexToRemove ->
                                notes.removeAt(indexToRemove)
                                notes.add(note)
                                this.updateAdapter()
                            }
                        }, { error ->
                            this.handleException(error)
                        })
            )
        }
    }

    private fun fetchLabels() {
        subscriptions.add(
            getAllLabelsInteractor.execute(Unit, { extractedLabels ->
                this.labels = extractedLabels
                this.fetchNotes()
            },{
                this.handleException(it)
            })
        )
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
        this.labels?.let {
            view?.updateAdapterWith(this.notes, it)
        }
    }
}