package com.lamadridblandongoyes.domain.interactors.labels

import com.lamadridblandongoyes.domain.interactors.base.ObservableUseCase
import com.lamadridblandongoyes.domain.models.Label
import com.lamadridblandongoyes.domain.repositories.ILocalStorageRepository
import io.reactivex.Observable
import io.reactivex.Scheduler

class DeleteLabelInteractor(
    mSubscribeOnScheduler: Scheduler,
    mObserveOnScheduler: Scheduler,
    private val localStorageRepository: ILocalStorageRepository
): ObservableUseCase<Int, Label>(mSubscribeOnScheduler, mObserveOnScheduler) {

    override fun buildUseCase(params: Label): Observable<Int> =
        localStorageRepository.deleteLabel(params)
}