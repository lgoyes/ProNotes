package com.lamadridblandongoyes.pronotes

import com.lamadridblandongoyes.pronotes.di.AppComponent
import com.lamadridblandongoyes.pronotes.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class ProNotesApplication: DaggerApplication() {

    var appComponent: AppComponent? = null

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val component = DaggerAppComponent.builder()
            .application(this)
            .build()
        appComponent = component
        component.inject(this)
        return component
    }
}