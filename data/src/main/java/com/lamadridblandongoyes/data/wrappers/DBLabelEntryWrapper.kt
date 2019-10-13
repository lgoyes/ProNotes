package com.lamadridblandongoyes.data.wrappers

import com.lamadridblandongoyes.data.models.database.DBLabelEntry
import com.lamadridblandongoyes.domain.models.Label
import io.reactivex.functions.Function

object DBLabelEntryWrapper: Function<DBLabelEntry, Label> {
    override fun apply(t: DBLabelEntry): Label {
        return Label(t.labelId, t.title, t.color)
    }
}