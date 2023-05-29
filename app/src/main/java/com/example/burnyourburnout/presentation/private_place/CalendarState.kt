package com.example.burnyourburnout.presentation.private_place

import com.example.burnyourburnout.data.entity.DiaryEntity

sealed class CalendarState {

    object Uninitialized: CalendarState()

    object Loading: CalendarState()

    data class Success(
        val diaryList: List<DiaryEntity>
    ): CalendarState()

    object Error: CalendarState()

}