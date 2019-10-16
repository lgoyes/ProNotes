package com.lamadridblandongoyes.pronotes.home

import com.lamadridblandongoyes.pronotes.architecturebasis.IErrorHandler
import io.reactivex.disposables.CompositeDisposable

class HomePresenter: HomeContract.Presenter {
    override var view: HomeContract.View? = null
    override val subscriptions = CompositeDisposable()
    override var errorHandler: IErrorHandler? = null
}