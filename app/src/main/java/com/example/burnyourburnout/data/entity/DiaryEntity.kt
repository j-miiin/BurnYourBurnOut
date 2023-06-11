package com.example.burnyourburnout.data.entity

import androidx.room.Entity

@Entity
data class DiaryEntity(
    val id: String,
    val year: Int,
    val month: Int,
    val day: Int,
    val content: String,
    val feeling: Int = 0
)
