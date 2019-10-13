package com.lamadridblandongoyes.domain.interactors.base

import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable

abstract class CompletableUseCase<P>(
    private val mSubscribeOnScheduler: Scheduler,
    private val mObserverOnSchedulers: Scheduler
): ICompletableUseCase<P> {

    abstract fun buildUseCase(params: P): Completable

    override fun execute(params: P,
                         onSuccessSubscriber: () -> Unit,
                         onErrorSubscriber: (error: Throwable) -> Unit,
                         doFinally: () -> Unit): Disposable {
        return buildUseCase(params)
            .subscribeOn(mSubscribeOnScheduler)
            .observeOn(mObserverOnSchedulers)
            .doFinally(doFinally)
            .subscribe(onSuccessSubscriber, onErrorSubscriber)
    }
}