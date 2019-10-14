package com.lamadridblandongoyes.pronotes.notes

import com.lamadridblandongoyes.domain.interactors.base.FlowableUseCase
import com.lamadridblandongoyes.domain.interactors.base.ObservableUseCase
import com.lamadridblandongoyes.domain.models.Note
import dagger.Module
import dagger.Provides

@Module
class NotesModule {
    @Provides
    fun provideNotesPresenter(
        getAllNotesInteractor: FlowableUseCase<List<Note>, Unit>,
        insertNoteInteractor: ObservableUseCase<Long, Note>
    ): NotesContract.Presenter =
        NotesPresenter(
            getAllNotesInteractor,
            insertNoteInteractor
        )
}