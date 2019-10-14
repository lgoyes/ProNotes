package com.lamadridblandongoyes.pronotes.noteedition

import dagger.Module
import dagger.Provides

@Module
class NoteEditionModule {
    @Provides
    fun provideNoteEditionPresenter(): NoteEditionContract.Presenter =
        NoteEditionPresenter()
}