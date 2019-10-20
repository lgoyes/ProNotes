package com.lamadridblandongoyes.pronotes.notes

import com.lamadridblandongoyes.domain.interactors.base.FlowableUseCase
import com.lamadridblandongoyes.domain.interactors.base.ObservableUseCase
import com.lamadridblandongoyes.domain.models.Label
import com.lamadridblandongoyes.domain.models.Note
import com.lamadridblandongoyes.pronotes.DELETE_NOTE_INTERACTOR
import com.lamadridblandongoyes.pronotes.UPDATE_NOTE_INTERACTOR
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class NotesModule {
    @Provides
    fun provideNotesPresenter(
        getAllNotesInteractor: FlowableUseCase<List<Note>, Unit>,
        getAllLabelsInteractor: FlowableUseCase<List<Label>, Unit>,
        insertNoteInteractor: ObservableUseCase<Long, Note>,
        @Named(UPDATE_NOTE_INTERACTOR) updateNoteInteractor: ObservableUseCase<Int, Note>,
        @Named(DELETE_NOTE_INTERACTOR) deleteNoteInteractor: ObservableUseCase<Int, Note>
    ): NotesContract.Presenter =
        NotesPresenter(
            getAllNotesInteractor,
            getAllLabelsInteractor,
            insertNoteInteractor,
            updateNoteInteractor,
            deleteNoteInteractor
        )
}