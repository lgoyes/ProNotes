package com.lamadridblandongoyes.domain.interactors.base

import io.reactivex.disposables.Disposable

interface ICompletableUseCase<P> {

    fun execute(
        params: P,
        onSuccessSubscriber: () -> Unit,
        onErrorSubscriber: (error: Throwable) -> Unit,
        doFinally: () -> Unit = {}): Disposable
}