package com.lamadridblandongoyes.pronotes.architecturebasis

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.CompositeDisposable

interface BasePresenter<T: BaseView>: IErrorProcessor, LifecycleObserver {
    var view: T?

    val subscriptions: CompositeDisposable?

    fun bind(view: T) {
        this.view = view
        errorHandler = view
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun unsubscribe() {
        view = null
        errorHandler = null
        subscriptions?.clear()
    }
}