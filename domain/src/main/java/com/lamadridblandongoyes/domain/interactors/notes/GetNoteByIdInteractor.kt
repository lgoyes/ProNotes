package com.lamadridblandongoyes.domain.interactors.notes

import com.lamadridblandongoyes.domain.interactors.base.FlowableUseCase
import com.lamadridblandongoyes.domain.models.Note
import com.lamadridblandongoyes.domain.repositories.ILocalStorageRepository
import io.reactivex.Flowable
import io.reactivex.Scheduler

class GetNoteByIdInteractor(
    mSubscribeOnScheduler: Scheduler,
    mObserveOnScheduler: Scheduler,
    private val localStorageRepository: ILocalStorageRepository
): FlowableUseCase<Note, Int>(mSubscribeOnScheduler, mObserveOnScheduler) {

    override fun buildUseCase(params: Int): Flowable<Note> =
        localStorageRepository.getNote(params)
}