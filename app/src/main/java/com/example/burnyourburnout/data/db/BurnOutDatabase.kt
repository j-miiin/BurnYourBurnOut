package com.example.burnyourburnout.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.burnyourburnout.data.db.dao.DiaryDao
import com.example.burnyourburnout.data.entity.DiaryEntity

@Database(
    entities = [DiaryEntity::class],
    version = 1,
    exportSchema = false
)
abstract class BurnOutDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "BurnOutDatabase.db"

        fun build(context: Context) : BurnOutDatabase =
            Room.databaseBuilder(context, BurnOutDatabase::class.java, DB_NAME).build()
    }

    abstract fun diaryDao(): DiaryDao
}