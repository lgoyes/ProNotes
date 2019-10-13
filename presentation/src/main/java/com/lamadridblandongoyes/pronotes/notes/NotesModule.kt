package com.lamadridblandongoyes.pronotes.notes

import dagger.Module
import dagger.Provides

@Module
class NotesModule {
    @Provides
    fun provideNotesPresenter(): NotesContract.Presenter = NotesPresenter()
}