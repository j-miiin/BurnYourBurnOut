package com.example.burnyourburnout.data.repository

import com.example.burnyourburnout.data.entity.DiaryEntity

interface DiaryRepository {

    suspend fun getDiaryList(year: Int, month: Int): List<DiaryEntity>

    suspend fun getDiary(id: String): DiaryEntity?

    suspend fun addDiary(diaryEntity: DiaryEntity)

    suspend fun updateDiary(diaryEntity: DiaryEntity)

    suspend fun deleteDiary(diaryEntity: DiaryEntity)
}