package com.lamadridblandongoyes.data.repositories

import com.lamadridblandongoyes.data.database.ILabelsDao
import com.lamadridblandongoyes.data.database.INotesDao
import com.lamadridblandongoyes.data.models.database.DBLabelEntry
import com.lamadridblandongoyes.data.models.database.DBNoteEntry
import com.lamadridblandongoyes.data.wrappers.DBLabelEntryWrapper
import com.lamadridblandongoyes.data.wrappers.DBNoteEntryWrapper
import com.lamadridblandongoyes.domain.models.Label
import com.lamadridblandongoyes.domain.models.Note
import com.lamadridblandongoyes.domain.repositories.ILocalStorageRepository
import io.reactivex.Flowable
import io.reactivex.Observable

class LocalStorageRepository(
    private val mNotesDao: INotesDao,
    private val mLabelsDao: ILabelsDao
): ILocalStorageRepository {
    override fun getAllNotes(): Flowable<List<Note>> {
        return mNotesDao.getAllNotes().map { dbNotes ->
            dbNotes.map {
                DBNoteEntryWrapper.map(it)
            }
        }
    }

    override fun getNote(noteId: Int): Flowable<Note> {
        return mNotesDao.getNote(noteId = noteId).map {
            DBNoteEntryWrapper.map(it)
        }
    }

    override fun insertNote(note: Note): Observable<Long> {
        return Observable.create<DBNoteEntry> { emitter ->
            emitter.onNext( DBNoteEntryWrapper.invert(note) )
            emitter.onComplete()
        }.flatMap { noteEntry ->
            Observable.create<Long> { emitter ->
                emitter.onNext( mNotesDao.insertNote(noteEntry) )
                emitter.onComplete()
            }
        }
    }

    override fun insertNotes(notes: List<Note>): Observable<Array<Long>> {
        return Observable.create<List<DBNoteEntry>> { emitter ->
                emitter.onNext( notes.map { note ->
                    DBNoteEntryWrapper.invert(note)
                })
                emitter.onComplete()
            }.flatMap { noteEntries ->
                Observable.create<Array<Long>> { emitter ->
                    emitter.onNext( mNotesDao.insertNotes(noteEntries) )
                    emitter.onComplete()
                }
        }
    }

    override fun deleteNote(note: Note): Observable<Int> {
        return Observable.create<DBNoteEntry> { emitter ->
            emitter.onNext( DBNoteEntryWrapper.invert(note) )
            emitter.onComplete()
        }.flatMap { noteEntry ->
            Observable.create<Int> { emitter ->
                emitter.onNext( mNotesDao.deleteNote(noteEntry) )
                emitter.onComplete()
            }
        }
    }

    override fun updateNote(note: Note): Observable<Int> {
        return Observable.create<DBNoteEntry> { emitter ->
            emitter.onNext( DBNoteEntryWrapper.invert(note) )
            emitter.onComplete()
        }.flatMap { noteEntry ->
            Observable.create<Int> { emitter ->
                emitter.onNext( mNotesDao.updateNote(noteEntry) )
                emitter.onComplete()
            }
        }
    }

    override fun getAllLabels(): Flowable<List<Label>> {
        return mLabelsDao.getAllLabels().map { dbLabels ->
            dbLabels.map {
                DBLabelEntryWrapper.map(it)
            }
        }
    }

    override fun getLabel(labelId: Int): Flowable<Label> {
        return mLabelsDao.getLabel(labelId = labelId).map {
            DBLabelEntryWrapper.map(it)
        }
    }

    override fun insertLabel(label: Label): Observable<Long> {
        return Observable.create<DBLabelEntry> { emitter ->
            emitter.onNext( DBLabelEntryWrapper.invert(label) )
            emitter.onComplete()
        }.flatMap { labelEntry ->
            Observable.create<Long> { emitter ->
                emitter.onNext( mLabelsDao.insertLabel(labelEntry) )
                emitter.onComplete()
            }
        }
    }

    override fun insertLabels(labels: List<Label>): Observable<Array<Long>> {
        return Observable.create<List<DBLabelEntry>> { emitter ->
            emitter.onNext( labels.map { label ->
                DBLabelEntryWrapper.invert(label)
            })
            emitter.onComplete()
        }.flatMap { labelEntries ->
            Observable.create<Array<Long>> { emitter ->
                emitter.onNext( mLabelsDao.insertLabels(labelEntries) )
                emitter.onComplete()
            }
        }
    }

    override fun deleteLabel(label: Label): Observable<Int> {
        return Observable.create<DBLabelEntry> { emitter ->
            emitter.onNext( DBLabelEntryWrapper.invert(label) )
            emitter.onComplete()
        }.flatMap { labelEntry ->
            Observable.create<Int> { emitter ->
                emitter.onNext( mLabelsDao.deleteLabel(labelEntry) )
                emitter.onComplete()
            }
        }
    }

    override fun updateLabel(label: Label): Observable<Int> {
        return Observable.create<DBLabelEntry> { emitter ->
            emitter.onNext( DBLabelEntryWrapper.invert(label) )
            emitter.onComplete()
        }.flatMap { labelEntry ->
            Observable.create<Int> { emitter ->
                emitter.onNext( mLabelsDao.updateLabels(labelEntry) )
                emitter.onComplete()
            }
        }
    }

}