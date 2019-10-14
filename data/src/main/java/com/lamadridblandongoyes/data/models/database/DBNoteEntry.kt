package com.lamadridblandongoyes.data.models.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lamadridblandongoyes.data.DATABASE_NOTES_TABLE_NAME
import java.io.Serializable

@Entity(
    tableName = DATABASE_NOTES_TABLE_NAME
)
class DBNoteEntry (
    @PrimaryKey(autoGenerate = true)
    val noteId: Int? = null,
    val labelId: Int? = null,
    val title: String,
    val description: String,
    val reminder: String? = null
): Serializable