package com.lamadridblandongoyes.data.wrappers

import com.lamadridblandongoyes.data.models.database.DBLabelEntry
import com.lamadridblandongoyes.domain.models.Label

object DBLabelEntryWrapper: IEncoderWrapper<DBLabelEntry, Label>, IDecoderWrapper<Label, DBLabelEntry> {
    override fun map(input: DBLabelEntry): Label {
        return Label(input.labelId, input.title, input.color)
    }

    override fun invert(input: Label): DBLabelEntry {
        return DBLabelEntry(input.labelId, input.title, input.color)
    }
}