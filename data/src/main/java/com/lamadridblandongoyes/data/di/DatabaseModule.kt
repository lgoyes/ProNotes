package com.lamadridblandongoyes.data.di

import android.content.Context
import androidx.room.Room
import com.lamadridblandongoyes.data.DATABASE_NAME
import com.lamadridblandongoyes.data.database.ILabelsDao
import com.lamadridblandongoyes.data.database.INotesDao
import com.lamadridblandongoyes.data.database.ProNotesDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideBaseRoom(context: Context): ProNotesDatabase {
        val builder = Room
            .databaseBuilder(context, ProNotesDatabase::class.java, DATABASE_NAME )
            .fallbackToDestructiveMigration()
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideNotesDao(database: ProNotesDatabase): INotesDao {
        return database.getNotesDao()
    }

    @Provides
    @Singleton
    fun provideLabelsDao(database: ProNotesDatabase): ILabelsDao {
        return database.getLabelsDao()
    }
}