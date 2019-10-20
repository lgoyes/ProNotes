package com.lamadridblandongoyes.pronotes.di

import com.lamadridblandongoyes.pronotes.home.HomeActivity
import com.lamadridblandongoyes.pronotes.home.HomeModule
import com.lamadridblandongoyes.pronotes.noteedition.NoteEditionActivity
import com.lamadridblandongoyes.pronotes.noteedition.NoteEditionModule
import com.lamadridblandongoyes.pronotes.notes.NotesModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [(NoteEditionModule::class)])
    abstract fun bindNoteEditionActivity(): NoteEditionActivity

    @ContributesAndroidInjector(modules = [HomeModule::class])
    abstract fun bindHomeActivity(): HomeActivity
}