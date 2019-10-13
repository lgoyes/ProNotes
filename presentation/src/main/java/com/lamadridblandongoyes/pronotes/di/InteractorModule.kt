package com.lamadridblandongoyes.pronotes.di

import com.lamadridblandongoyes.data.di.RepositoryModule
import dagger.Module

@Module(includes = [RepositoryModule::class, SchedulerModule::class])
class InteractorModule {

}