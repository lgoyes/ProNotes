package com.lamadridblandongoyes.pronotes.noteedition

import com.lamadridblandongoyes.pronotes.architecturebasis.BasePresenter
import com.lamadridblandongoyes.pronotes.architecturebasis.IErrorHandler
import io.reactivex.disposables.CompositeDisposable

class NoteEditionPresenter: BasePresenter<NoteEditionContract.View>, NoteEditionContract.Presenter {
    override var view: NoteEditionContract.View? = null
    override var errorHandler: IErrorHandler? = null
    override val subscriptions = CompositeDisposable()
}