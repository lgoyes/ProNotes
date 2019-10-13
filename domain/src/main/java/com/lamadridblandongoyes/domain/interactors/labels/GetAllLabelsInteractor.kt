package com.lamadridblandongoyes.domain.interactors.labels

import com.lamadridblandongoyes.domain.interactors.base.FlowableUseCase
import com.lamadridblandongoyes.domain.models.Label
import com.lamadridblandongoyes.domain.repositories.ILocalStorageRepository
import io.reactivex.Flowable
import io.reactivex.Scheduler

class GetAllLabelsInteractor(
    mSubscribeOnScheduler: Scheduler,
    mObserveOnScheduler: Scheduler,
    private val localStorageRepository: ILocalStorageRepository
): FlowableUseCase<List<Label>, Unit>(mSubscribeOnScheduler, mObserveOnScheduler) {

    override fun buildUseCase(params: Unit): Flowable<List<Label>> =
        localStorageRepository.getAllLabels()

}