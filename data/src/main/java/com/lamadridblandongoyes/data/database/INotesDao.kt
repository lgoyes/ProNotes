package com.lamadridblandongoyes.data.database

import androidx.room.*
import com.lamadridblandongoyes.data.DATABASE_NOTES_TABLE_NAME
import com.lamadridblandongoyes.data.models.notes.DBNoteEntry
import io.reactivex.Flowable

@Dao
interface INotesDao {
    @Query("SELECT * FROM $DATABASE_NOTES_TABLE_NAME")
    fun getAllNotes(): Flowable<List<DBNoteEntry>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertNote(noteEntry: DBNoteEntry) : Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNotes(noteEntries: List<DBNoteEntry>) : Array<Long>

    @Delete
    fun deleteNote(noteEntry: DBNoteEntry): Int

    @Update
    fun updateNote(noteEntry: DBNoteEntry): Int

    @Query("SELECT * FROM $DATABASE_NOTES_TABLE_NAME WHERE noteId = :noteId")
    fun getNote(noteId : Int): Flowable<DBNoteEntry>
}
