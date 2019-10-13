package com.lamadridblandongoyes.pronotes.di

import com.lamadridblandongoyes.data.di.RepositoryModule
import com.lamadridblandongoyes.domain.IO_THREAD_SCHEDULER
import com.lamadridblandongoyes.domain.MAIN_THREAD_SCHEDULER
import com.lamadridblandongoyes.domain.interactors.base.FlowableUseCase
import com.lamadridblandongoyes.domain.interactors.base.ObservableUseCase
import com.lamadridblandongoyes.domain.interactors.labels.*
import com.lamadridblandongoyes.domain.interactors.notes.*
import com.lamadridblandongoyes.domain.models.Label
import com.lamadridblandongoyes.domain.models.Note
import com.lamadridblandongoyes.domain.repositories.ILocalStorageRepository
import com.lamadridblandongoyes.pronotes.DELETE_LABEL_INTERACTOR
import com.lamadridblandongoyes.pronotes.DELETE_NOTE_INTERACTOR
import com.lamadridblandongoyes.pronotes.UPDATE_LABEL_INTERACTOR
import com.lamadridblandongoyes.pronotes.UPDATE_NOTE_INTERACTOR
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [RepositoryModule::class, SchedulerModule::class])
class InteractorModule {

    @Singleton
    @Provides
    @Named(DELETE_LABEL_INTERACTOR)
    fun provideDeleteLabelInteractor(
        @Named(IO_THREAD_SCHEDULER) subscribeOnScheduler: Scheduler,
        @Named(MAIN_THREAD_SCHEDULER) observerOnScheduler: Scheduler,
        localStorageRepository: ILocalStorageRepository
    ): ObservableUseCase<Int, Label> =
        DeleteLabelInteractor(
            subscribeOnScheduler,
            observerOnScheduler,
            localStorageRepository)

    @Singleton
    @Provides
    fun provideGetAllLabelsInteractor(
        @Named(IO_THREAD_SCHEDULER) subscribeOnScheduler: Scheduler,
        @Named(MAIN_THREAD_SCHEDULER) observerOnScheduler: Scheduler,
        localStorageRepository: ILocalStorageRepository
    ): FlowableUseCase<List<Label>, Unit> =
        GetAllLabelsInteractor(
            subscribeOnScheduler,
            observerOnScheduler,
            localStorageRepository)

    @Singleton
    @Provides
    fun provideGetLabelByIdInteractor(
        @Named(IO_THREAD_SCHEDULER) subscribeOnScheduler: Scheduler,
        @Named(MAIN_THREAD_SCHEDULER) observerOnScheduler: Scheduler,
        localStorageRepository: ILocalStorageRepository
    ): FlowableUseCase<Label, Int> =
        GetLabelByIdInteractor(
            subscribeOnScheduler,
            observerOnScheduler,
            localStorageRepository)

    @Singleton
    @Provides
    fun provideInsertLabelInteractor(
        @Named(IO_THREAD_SCHEDULER) subscribeOnScheduler: Scheduler,
        @Named(MAIN_THREAD_SCHEDULER) observerOnScheduler: Scheduler,
        localStorageRepository: ILocalStorageRepository
    ): ObservableUseCase<Long, Label> =
        InsertLabelInteractor(
            subscribeOnScheduler,
            observerOnScheduler,
            localStorageRepository)

    @Singleton
    @Provides
    @Named(UPDATE_LABEL_INTERACTOR)
    fun provideUpdateLabelInteractor(
        @Named(IO_THREAD_SCHEDULER) subscribeOnScheduler: Scheduler,
        @Named(MAIN_THREAD_SCHEDULER) observerOnScheduler: Scheduler,
        localStorageRepository: ILocalStorageRepository
    ): ObservableUseCase<Int, Label> =
        UpdateLabelInteractor(
            subscribeOnScheduler,
            observerOnScheduler,
            localStorageRepository)

    @Singleton
    @Provides
    @Named(DELETE_NOTE_INTERACTOR)
    fun provideDeleteNoteInteractor(
        @Named(IO_THREAD_SCHEDULER) subscribeOnScheduler: Scheduler,
        @Named(MAIN_THREAD_SCHEDULER) observerOnScheduler: Scheduler,
        localStorageRepository: ILocalStorageRepository
    ): ObservableUseCase<Int, Note> =
        DeleteNoteInteractor(
            subscribeOnScheduler,
            observerOnScheduler,
            localStorageRepository)

    @Singleton
    @Provides
    fun provideGetAllNotesInteractor(
        @Named(IO_THREAD_SCHEDULER) subscribeOnScheduler: Scheduler,
        @Named(MAIN_THREAD_SCHEDULER) observerOnScheduler: Scheduler,
        localStorageRepository: ILocalStorageRepository
    ): FlowableUseCase<List<Note>, Unit> =
        GetAllNotesInteractor(
            subscribeOnScheduler,
            observerOnScheduler,
            localStorageRepository)

    @Singleton
    @Provides
    fun provideGetNoteByIdInteractor(
        @Named(IO_THREAD_SCHEDULER) subscribeOnScheduler: Scheduler,
        @Named(MAIN_THREAD_SCHEDULER) observerOnScheduler: Scheduler,
        localStorageRepository: ILocalStorageRepository
    ): FlowableUseCase<Note, Int> =
        GetNoteByIdInteractor(
            subscribeOnScheduler,
            observerOnScheduler,
            localStorageRepository)

    @Singleton
    @Provides
    fun provideInsertNoteInteractor(
        @Named(IO_THREAD_SCHEDULER) subscribeOnScheduler: Scheduler,
        @Named(MAIN_THREAD_SCHEDULER) observerOnScheduler: Scheduler,
        localStorageRepository: ILocalStorageRepository
    ): ObservableUseCase<Long, Note> =
        InsertNoteInteractor(
            subscribeOnScheduler,
            observerOnScheduler,
            localStorageRepository)

    @Singleton
    @Provides
    @Named(UPDATE_NOTE_INTERACTOR)
    fun provideUpdateNoteInteractor(
        @Named(IO_THREAD_SCHEDULER) subscribeOnScheduler: Scheduler,
        @Named(MAIN_THREAD_SCHEDULER) observerOnScheduler: Scheduler,
        localStorageRepository: ILocalStorageRepository
    ): ObservableUseCase<Int, Note> =
        UpdateNoteInteractor(
            subscribeOnScheduler,
            observerOnScheduler,
            localStorageRepository)
}