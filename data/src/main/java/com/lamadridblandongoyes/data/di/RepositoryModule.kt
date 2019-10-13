package com.lamadridblandongoyes.data.di

import com.lamadridblandongoyes.data.database.ILabelsDao
import com.lamadridblandongoyes.data.database.INotesDao
import com.lamadridblandongoyes.data.repositories.LocalStorageRepository
import com.lamadridblandongoyes.domain.repositories.ILocalStorageRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ServiceProviderModule::class, DatabaseModule::class])
class RepositoryModule {
    @Singleton
    @Provides
    fun provideLocalRepository(mNotesDao: INotesDao, mLabelsDao: ILabelsDao):
            ILocalStorageRepository = LocalStorageRepository(mNotesDao,mLabelsDao)
}