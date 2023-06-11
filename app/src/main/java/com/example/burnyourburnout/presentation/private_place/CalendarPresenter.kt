package com.example.burnyourburnout.presentation.private_place

import com.example.burnyourburnout.data.entity.DiaryEntity
import com.example.burnyourburnout.ext.getSelectedDateString
import com.example.burnyourburnout.ext.getTodayDate
import com.example.burnyourburnout.usecase.private_place.AddDiaryUseCase
import com.example.burnyourburnout.usecase.private_place.GetDiaryUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class CalendarPresenter(
    private val view: CalendarContract.View,
    private val getDiaryUseCase: GetDiaryUseCase,
    private val addDiaryUseCase: AddDiaryUseCase
): CalendarContract.Presenter {

    override val scope: CoroutineScope = MainScope()

    override fun onViewCreated() {
        val date = getTodayDate()
        fetchCalendarRecord(date[0], date[1] + 1)
    }

    override fun onDestroyView() {}

    override fun fetchCalendarRecord(year: Int, month: Int) {
        scope.launch {
            val calendar = Calendar.getInstance()
            calendar.set(year, month-1, 1)
            val lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

            var dayRecordList = ArrayList<DiaryEntity>()
            for (i in 1..lastDay) {
                var dayRecordEntity = getDiaryUseCase(getSelectedDateString(year, month, i))
                if (dayRecordEntity == null) {
                    dayRecordList.add(0)
                }
                else dayRecordList.add(dayRecordEntity.feeling)
            }

            view.showCalendarRecord(dayRecordList)
        }
    }
}