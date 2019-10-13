package com.lamadridblandongoyes.pronotes.di

import com.lamadridblandongoyes.pronotes.notes.NotesActivity
import com.lamadridblandongoyes.pronotes.notes.NotesModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [(NotesModule::class)])
    abstract fun bindNotesActivity(): NotesActivity
}