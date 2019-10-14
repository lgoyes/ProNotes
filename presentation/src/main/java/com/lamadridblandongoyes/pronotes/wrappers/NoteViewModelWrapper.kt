package com.lamadridblandongoyes.pronotes.wrappers

import com.lamadridblandongoyes.domain.models.Note
import com.lamadridblandongoyes.pronotes.noteedition.NoteEditionContract

object NoteViewModelWrapper {
    fun map(input: NoteEditionContract.View.ViewModel): Note {
        return Note(
            null,
            null,
            input.title,
            input.description,
            input.reminderDate)
    }
}