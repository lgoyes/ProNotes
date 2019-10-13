package com.lamadridblandongoyes.domain.interactors.base

import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.Disposable

abstract class SingleUseCase<R, P>(
    private val mSubscribeOnScheduler: Scheduler,
    private val mObserverOnSchedulers: Scheduler
): IUseCase<R, P> {

    abstract fun buildUseCase(params: P): Single<R>

    override fun execute(
        params: P,
        onSuccessSubscriber: (response: R) -> Unit,
        onErrorSubscriber: (error: Throwable) -> Unit,
        doFinally: () -> Unit): Disposable {

        return buildUseCase(params).subscribeOn(mSubscribeOnScheduler)
            .observeOn(mObserverOnSchedulers)
            .doFinally(doFinally)
            .subscribe(onSuccessSubscriber, onErrorSubscriber)
    }
}