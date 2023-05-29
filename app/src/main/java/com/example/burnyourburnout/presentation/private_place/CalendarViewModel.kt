package com.example.burnyourburnout.presentation.private_place

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.burnyourburnout.data.entity.DiaryEntity
import com.example.burnyourburnout.presentation.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

internal class CalendarViewModel: BaseViewModel() {

    val scope: CoroutineScope = MainScope()

    var calendarStateLiveData = MutableLiveData<CalendarState>(CalendarState.Uninitialized)

    override fun fetchData() = scope.launch {
        setState(
            CalendarState.Loading
        )
        setState(
            CalendarState.Success(
                getMockList()
            )
        )
    }

    private fun setState(state: CalendarState) {
        calendarStateLiveData.postValue(state)
    }

    private fun getMockList(): List<DiaryEntity> {
        val calendar = Calendar.getInstance()
        calendar.set(2023, 5-1, 1)
        val lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        var id: Long = 0

        var dayRecordList = ArrayList<DiaryEntity>()
        for (i in 1..lastDay) {
            dayRecordList.add(DiaryEntity(id, 2023, 5, i, "", (0..6).random()))
            id += 1
        }

        return dayRecordList
    }

}