package com.example.burnyourburnout.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.burnyourburnout.data.entity.DiaryEntity

@Dao
interface DiaryDao {

    @Query("SELECT * FROM DiaryEntity WHERE year=:year AND month=:month")
    suspend fun getAll(year: Int, month: Int): List<DiaryEntity>

    @Query("SELECT * FROM DiaryEntity WHERE id=:id")
    suspend fun get(id: String): DiaryEntity?

    @Insert
    suspend fun insert(diaryEntity: DiaryEntity)

    @Update
    suspend fun update(diaryEntity: DiaryEntity)

    @Delete
    suspend fun delete(diaryEntity: DiaryEntity)
}