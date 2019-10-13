package com.lamadridblandongoyes.pronotes.notes

import com.lamadridblandongoyes.pronotes.architecturebasis.IErrorHandler
import io.reactivex.disposables.CompositeDisposable

class NotesPresenter: NotesContract.Presenter {
    override var view: NotesContract.View? = null
    override var errorHandler: IErrorHandler? = null
    override val subscriptions = CompositeDisposable()
}