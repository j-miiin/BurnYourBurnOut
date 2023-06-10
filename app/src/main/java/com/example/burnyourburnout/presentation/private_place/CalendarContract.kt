package com.example.burnyourburnout.presentation.private_place

import com.example.burnyourburnout.BasePresenter
import com.example.burnyourburnout.BaseView
import com.example.burnyourburnout.data.entity.DiaryEntity

interface CalendarContract {

    interface View : BaseView<Presenter> {

        fun showLoadingIndicator()

        fun hideLoadingIndicator()

        fun showErrorDescription(message: String)

        fun showCalendarRecord(dayRecordList: ArrayList<DiaryEntity>)
    }

    interface Presenter : BasePresenter {

        fun fetchCalendarRecord(year:Int, month: Int)
    }
}