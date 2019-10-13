package com.lamadridblandongoyes.pronotes.notes

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


}