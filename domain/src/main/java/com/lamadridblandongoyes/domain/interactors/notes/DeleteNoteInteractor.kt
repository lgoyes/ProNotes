package com.lamadridblandongoyes.domain.interactors.notes

import com.lamadridblandongoyes.domain.interactors.base.ObservableUseCase
import com.lamadridblandongoyes.domain.models.Note
import com.lamadridblandongoyes.domain.repositories.ILocalStorageRepository
import io.reactivex.Observable
import io.reactivex.Scheduler

class DeleteNoteInteractor(
    mSubscribeOnScheduler: Scheduler,
    mObserveOnScheduler: Scheduler,
    private val localStorageRepository: ILocalStorageRepository
): ObservableUseCase<Int, Note>(mSubscribeOnScheduler, mObserveOnScheduler) {

    override fun buildUseCase(params: Note): Observable<Int> =
        localStorageRepository.deleteNote(params)
}