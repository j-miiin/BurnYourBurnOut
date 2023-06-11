package com.example.burnyourburnout.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DiaryEntity(
    @PrimaryKey val id: String,
    val year: Int,
    val month: Int,
    val day: Int,
    val content: String,
    val feeling: Int = 0
)
