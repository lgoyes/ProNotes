package com.lamadridblandongoyes.pronotes.di

import android.content.Context
import com.lamadridblandongoyes.data.di.HelperModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [(AndroidSupportInjectionModule::class),
        (AppModule::class),
        (ActivityBuilder::class),
        (FragmentBuilder::class),
        (InteractorModule::class),
        (HelperModule::class)]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Context): Builder

        fun build(): AppComponent
    }
}