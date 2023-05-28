package com.example.burnyourburnout.data.entity

data class DiaryEntity(
    val id: Long,
    val year: Int,
    val month: Int,
    val day: Int,
    val content: String,
    val feeling: Int = 0
)
