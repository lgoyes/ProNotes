package com.lamadridblandongoyes.data.wrappers

import com.lamadridblandongoyes.data.models.database.DBNoteEntry
import com.lamadridblandongoyes.domain.models.Note
import io.reactivex.functions.Function

object DBNoteEntryWrapper: Function<DBNoteEntry, Note> {
    override fun apply(t: DBNoteEntry): Note {
        return Note(t.noteId, t.labelId, t.title, t.description, t.reminder)
    }
}