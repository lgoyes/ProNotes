package com.lamadridblandongoyes.data.wrappers

import com.lamadridblandongoyes.data.models.database.DBNoteEntry
import com.lamadridblandongoyes.domain.models.Note

object DBNoteEntryWrapper: IEncoderWrapper<DBNoteEntry, Note>, IDecoderWrapper<Note, DBNoteEntry> {
    override fun map(input: DBNoteEntry): Note {
        return Note(
            input.noteId,
            input.labelId,
            input.title,
            input.description,
            input.reminder)
    }

    override fun invert(input: Note): DBNoteEntry {
        return DBNoteEntry(
            input.noteId,
            input.labelId,
            input.title,
            input.description,
            input.reminder)
    }
}