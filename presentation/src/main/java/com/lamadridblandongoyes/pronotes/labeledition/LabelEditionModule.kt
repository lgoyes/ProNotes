package com.lamadridblandongoyes.pronotes.labeledition

import dagger.Module
import dagger.Provides

@Module
class LabelEditionModule {
    @Provides
    fun provideLabelEditionPresenter(): LabelEditionContract.Presenter =
        LabelEditionPresenter()
}