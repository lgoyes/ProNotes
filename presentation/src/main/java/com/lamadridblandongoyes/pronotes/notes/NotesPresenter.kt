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

    private fun storeInDataBase(indexToBeStored: Int) {
        if (indexToBeStored > notes.size - 1) {
            return
        }

        subscriptions.add(
            insertNoteInteractor.execute(notes[indexToBeStored],{
                storeInDataBase(indexToBeStored + 1)
            }, {
                this.handleException(it)
            })
        )
    }

    private fun fetchNotes() {
        subscriptions.add(
            getAllNotesInteractor.execute(Unit,{ extractedNotes ->
                if (extractedNotes.isNotEmpty()) {
                    view?.let {
                        it.updateAdapterWith( ArrayList(extractedNotes) )
                    }
                } else {
                    addNotes()
                    storeInDataBase(0)
                    view?.let {
                        it.updateAdapterWith( this.notes )
                    }
                }
            },{
                this.handleException(it)
            })
        )
    }

    private fun addNotes() {
        notes.addAll(
            arrayListOf(
                Note(
                null,
                1,
                "Title note 1",
                "Description note 1",
                null),
                Note(
                    null,
                1,
                "Title note 2",
                "Description note 2",
                null),
                Note(
                    null,
                1,
                "Title note 3",
                "Description note 2. This is a larger description and I want to check if the height increases.",
                null),
                Note(
                    null,
                1,
                "Title note 4",
                "This note has a short description.",
                null),
                Note(
                    null,
                1,
                "Title note 5",
                "And this is the last note of all. I hope I can write a kind of cool description. However, the most possible thing it's that is not going to be that long.",
                null),
                Note(
                0,
                1,
                "Title note 6",
                "Description note 6",
                null),
                Note(
                    null,
                1,
                "Title note 7",
                "Description note 7",
                null),
                Note(
                    null,
                1,
                "Title note 8",
                "Description note 8. This is a larger description and I want to check if the height increases.",
                null),
                Note(
                    null,
                1,
                "Title note 9",
                "This note has a short description.",
                null),
                Note(
                    null,
                1,
                "Title note 10",
                "And this is the last note of all. I hope I can write a kind of cool description. However, the most possible thing it's that is not going to be that long.",
                null),
                Note(
                    null,
                1,
                "Title note 11",
                "Description note 11",
                null),
                Note(
                    null,
                1,
                "Title note 12",
                "Description note 12",
                null),
                Note(
                    null,
                1,
                "Title note 13",
                "Description note 13. This is a larger description and I want to check if the height increases.",
                null),
                Note(
                    null,
                1,
                "Title note 14",
                "This note has a short description.",
                null),
                Note(
                    null,
                1,
                "Title note 15",
                "And this is the last note of all. I hope I can write a kind of cool description. However, the most possible thing it's that is not going to be that long.",
                null),
                Note(
                    null,
                1,
                "Title note 16",
                "Description note 16",
                null),
                Note(
                    null,
                1,
                "Title note 17",
                "Description note 17",
                null),
                Note(
                    null,
                1,
                "Title note 18",
                "Description note 18. This is a larger description and I want to check if the height increases.",
                null),
                Note(
                    null,
                1,
                "Title note 19",
                "This note has a short description.",
                null),
                Note(
                    null,
                1,
                "Title note 20",
                "And this is the last note of all. I hope I can write a kind of cool description. However, the most possible thing it's that is not going to be that long.",
                null)
            )
        )
    }
}