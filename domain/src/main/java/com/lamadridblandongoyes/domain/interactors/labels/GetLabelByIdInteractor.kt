package com.lamadridblandongoyes.domain.interactors.labels

import com.lamadridblandongoyes.domain.interactors.base.FlowableUseCase
import com.lamadridblandongoyes.domain.models.Label
import com.lamadridblandongoyes.domain.repositories.ILocalStorageRepository
import io.reactivex.Flowable
import io.reactivex.Scheduler

class GetLabelByIdInteractor(
    mSubscribeOnScheduler: Scheduler,
    mObserveOnScheduler: Scheduler,
    private val localStorageRepository: ILocalStorageRepository
): FlowableUseCase<Label, Int>(mSubscribeOnScheduler, mObserveOnScheduler) {

    override fun buildUseCase(params: Int): Flowable<Label> =
        localStorageRepository.getLabel(params)
}