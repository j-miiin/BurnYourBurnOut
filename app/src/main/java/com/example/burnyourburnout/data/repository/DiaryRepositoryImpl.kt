package com.example.burnyourburnout.data.repository

import com.example.burnyourburnout.data.db.dao.DiaryDao
import com.example.burnyourburnout.data.entity.DiaryEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DiaryRepositoryImpl(
    private val diaryDao: DiaryDao,
    private val dispatcher: CoroutineDispatcher
) : DiaryRepository {
    override suspend fun getDiaryList(year: Int, month: Int): List<DiaryEntity> = withContext(dispatcher) {
        diaryDao.getAll(year, month)
    }

    override suspend fun getDiary(id: String): DiaryEntity?  = withContext(dispatcher) {
        diaryDao.get(id)
    }

    override suspend fun addDiary(diaryEntity: DiaryEntity)  = withContext(dispatcher) {
        diaryDao.insert(diaryEntity)
    }

    override suspend fun updateDiary(diaryEntity: DiaryEntity)  = withContext(dispatcher) {
        diaryDao.update(diaryEntity)
    }

    override suspend fun deleteDiary(diaryEntity: DiaryEntity)  = withContext(dispatcher) {
        diaryDao.delete(diaryEntity)
    }
}