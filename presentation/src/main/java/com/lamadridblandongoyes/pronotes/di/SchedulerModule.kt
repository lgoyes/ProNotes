package com.lamadridblandongoyes.pronotes.di

import com.lamadridblandongoyes.domain.IO_THREAD_SCHEDULER
import com.lamadridblandongoyes.domain.MAIN_THREAD_SCHEDULER
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named

@Module
class SchedulerModule {

    @Provides
    @Named(IO_THREAD_SCHEDULER)
    fun provideIOScheduler(): Scheduler = Schedulers.io()

    @Provides
    @Named(MAIN_THREAD_SCHEDULER)
    fun provideMainThreadScheduler(): Scheduler = AndroidSchedulers.mainThread()
}