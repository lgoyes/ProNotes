package com.lamadridblandongoyes.data.database

import androidx.room.*
import com.lamadridblandongoyes.data.DATABASE_LABELS_TABLE_NAME
import com.lamadridblandongoyes.data.models.labels.DBLabelEntry
import io.reactivex.Flowable

@Dao
interface ILabelsDao {
    @Query("SELECT * FROM $DATABASE_LABELS_TABLE_NAME")
    fun getAllLabels(): Flowable<List<DBLabelEntry>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertLabel(labelEntry: DBLabelEntry) : Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertLabels(labelEntries: List<DBLabelEntry>) : Array<Long>

    @Delete
    fun deleteLabels(labelEntry: DBLabelEntry): Int

    @Update
    fun updateLabels(labelEntry: DBLabelEntry): Int

    @Query("SELECT * FROM $DATABASE_LABELS_TABLE_NAME WHERE labelId = :labelId")
    fun getLabel(labelId : Int): Flowable<DBLabelEntry>
}