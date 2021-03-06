package com.lamadridblandongoyes.data.models.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lamadridblandongoyes.data.DATABASE_LABELS_TABLE_NAME
import java.io.Serializable

@Entity(
    tableName = DATABASE_LABELS_TABLE_NAME
)
class DBLabelEntry (
    @PrimaryKey
    val labelId: Int? = null,
    val title: String,
    val color: String
): Serializable