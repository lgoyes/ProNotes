package com.lamadridblandongoyes.domain.repositories

import com.lamadridblandongoyes.domain.models.Label
import com.lamadridblandongoyes.domain.models.Note
import io.reactivex.Flowable
import io.reactivex.Observable

interface ILocalStorageRepository {
    fun getAllNotes(): Flowable<List<Note>>
    fun getNote(noteId : Int ) : Flowable<Note>
    fun insertNote(note: Note): Observable<Long>
    fun insertNotes(notes: List<Note>): Observable<Array<Long>>
    fun deleteNote(note: Note): Observable<Int>
    fun updateNote(note: Note): Observable<Int>

    fun getAllLabels(): Flowable<List<Label>>
    fun getLabel(labelId : Int ) : Flowable<Label>
    fun insertLabel(label: Label): Observable<Long>
    fun insertLabels(labels: List<Label>): Observable<Array<Long>>
    fun deleteLabel(label: Label): Observable<Int>
    fun updateLabel(label: Label): Observable<Int>
}