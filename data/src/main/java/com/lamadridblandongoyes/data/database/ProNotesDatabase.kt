package com.lamadridblandongoyes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lamadridblandongoyes.data.DATABASE_VERSION
import com.lamadridblandongoyes.data.models.database.DBLabelEntry
import com.lamadridblandongoyes.data.models.database.DBNoteEntry

@Database(
    entities = [DBNoteEntry::class, DBLabelEntry::class],
    version = DATABASE_VERSION,
    exportSchema = false)
abstract class ProNotesDatabase: RoomDatabase() {
    abstract fun getNotesDao(): INotesDao
    abstract fun getLabelsDao(): ILabelsDao
}