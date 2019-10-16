package com.lamadridblandongoyes.pronotes.home

import dagger.Module
import dagger.Provides

@Module
class HomeModule {
    @Provides
    fun provideHomePresenter(
    ): HomeContract.Presenter =
        HomePresenter()
}