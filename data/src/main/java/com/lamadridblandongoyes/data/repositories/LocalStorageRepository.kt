package com.lamadridblandongoyes.data.repositories

import com.lamadridblandongoyes.data.database.ILabelsDao
import com.lamadridblandongoyes.data.database.INotesDao
import com.lamadridblandongoyes.domain.repositories.ILocalStorageRepository

class LocalStorageRepository(
    private val mNotesDao: INotesDao,
    private val mLabelsDao: ILabelsDao
): ILocalStorageRepository {

}