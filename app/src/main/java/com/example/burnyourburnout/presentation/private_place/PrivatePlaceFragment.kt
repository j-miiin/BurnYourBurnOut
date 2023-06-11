package com.example.burnyourburnout.presentation.private_place

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.burnyourburnout.data.entity.DiaryEntity
import com.example.burnyourburnout.databinding.FragmentPrivatePlaceBinding
import com.example.burnyourburnout.ext.getLastOrNextMonthOrDay
import com.example.burnyourburnout.ext.getTodayDate
import com.example.burnyourburnout.ext.toGone
import com.example.burnyourburnout.ext.toVisible
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.ScopeFragment
import java.util.*
import kotlin.collections.ArrayList

class PrivatePlaceFragment : ScopeFragment(), CalendarContract.View {

    private lateinit var binding: FragmentPrivatePlaceBinding

    private var selectedYear = 0
    private var selectedMonth = 0
    private var selectedDay = 0

    override val presenter: CalendarContract.Presenter by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPrivatePlaceBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        presenter.onViewCreated()
    }

    private fun initViews() = with(binding) {
        val dateInfo = getTodayDate()
        selectedYear = dateInfo[0]
        selectedMonth = dateInfo[1] + 1
        selectedDay = dateInfo[2]

        updateDate(selectedYear, selectedMonth)

        lastMonthButton.setOnClickListener {
            val lastMonthDateInfo = getLastOrNextMonthOrDay(selectedYear, selectedMonth-1, selectedDay, true, true)
            selectedYear = lastMonthDateInfo[0]
            selectedMonth = lastMonthDateInfo[1] + 1
            selectedDay = lastMonthDateInfo[2]

            updateDate(selectedYear, selectedMonth)
            presenter.fetchCalendarRecord(selectedYear, selectedMonth)
        }

        nextMonthDayButton.setOnClickListener {
            val nextMonthDateInfo = getLastOrNextMonthOrDay(selectedYear, selectedMonth-1, selectedDay, false, true)
            selectedYear = nextMonthDateInfo[0]
            selectedMonth = nextMonthDateInfo[1] + 1
            selectedDay = nextMonthDateInfo[2]

            updateDate(selectedYear, selectedMonth)
            presenter.fetchCalendarRecord(selectedYear, selectedMonth)
        }

        recyclerView.apply {
            layoutManager = GridLayoutManager(context, 7)
            adapter = CalendarAdapter()
        }
    }

    private fun updateDate(selectedYear: Int, selectedMonth: Int) {
        binding.yearTextView.text = "${selectedYear}년"
        binding.monthTextView.text = "${selectedMonth}월"
    }

    override fun showLoadingIndicator() {
        binding.progressBar.toVisible()
    }

    override fun hideLoadingIndicator() {
        binding.progressBar.toGone()
    }

    override fun showErrorDescription(message: String) {
        binding.recyclerView.toGone()
        binding.errorDescriptionTextView.toVisible()
        binding.errorDescriptionTextView.text = message
    }

    override fun showCalendarRecord(dayRecordList: ArrayList<DiaryEntity>) {
        binding.recyclerView.toVisible()
        binding.errorDescriptionTextView.toGone()

        (binding.recyclerView.adapter as CalendarAdapter).run {
            setDayList(
                getDayList(),
                dayRecordList,
                dayCellClickListener = {
                    presenter.updateDiary(it)
                }
            )
        }
    }

    private fun getDayList(): ArrayList<String> {
        var dayList = ArrayList<String>()

        val calendar = Calendar.getInstance()
        calendar.set(selectedYear, selectedMonth-1, 1)
        val lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        val firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

        for (i in 1..42) {
            if (i < firstDayOfWeek || i >= (lastDay + firstDayOfWeek)) dayList.add("")
            else dayList.add((i - firstDayOfWeek + 1).toString())
        }

        var check = true
        for (i in 35..41) {
            if (dayList[i] != "") check = false
        }
        if (check) {
            for (i in 1..7) dayList.removeLast()
        }

        return dayList
    }
}