package com.lamadridblandongoyes.pronotes.home

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.lamadridblandongoyes.pronotes.architecturebasis.IErrorHandler
import io.reactivex.disposables.CompositeDisposable

class HomePresenter: HomeContract.Presenter {
    override var view: HomeContract.View? = null
    override val subscriptions = CompositeDisposable()
    override var errorHandler: IErrorHandler? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun start() {
        
    }
}