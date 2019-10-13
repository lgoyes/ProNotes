package com.lamadridblandongoyes.pronotes.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val mApplicationContext : Context) {
    @Provides
    @Singleton
    fun provideContext(): Context = mApplicationContext
}