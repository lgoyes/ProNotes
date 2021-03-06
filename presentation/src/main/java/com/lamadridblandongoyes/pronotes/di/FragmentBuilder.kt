package com.lamadridblandongoyes.pronotes.di

import com.lamadridblandongoyes.pronotes.labels.LabelsFragment
import com.lamadridblandongoyes.pronotes.labels.LabelsModule
import com.lamadridblandongoyes.pronotes.notes.NotesFragment
import com.lamadridblandongoyes.pronotes.notes.NotesModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {
    @ContributesAndroidInjector(modules = [(NotesModule::class)])
    abstract fun bindNotesFragment(): NotesFragment

    @ContributesAndroidInjector(modules = [(LabelsModule::class)])
    abstract fun bindLabelsFragment(): LabelsFragment
}