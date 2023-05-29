package com.example.burnyourburnout.presentation.private_place

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.example.burnyourburnout.databinding.FragmentPrivatePlaceBinding
import com.example.burnyourburnout.ext.getLastOrNextMonthOrDay
import com.example.burnyourburnout.ext.getTodayDate
import com.example.burnyourburnout.ext.toGone
import com.example.burnyourburnout.ext.toVisible
import com.example.burnyourburnout.presentation.BaseFragment
import java.util.*
import kotlin.collections.ArrayList

internal class PrivatePlaceFragment : BaseFragment<CalendarViewModel, FragmentPrivatePlaceBinding>() {

    private var selectedYear = 0
    private var selectedMonth = 0
    private var selectedDay = 0

    override val viewModel: CalendarViewModel
        get() = CalendarViewModel()

    override fun getViewBinding(): FragmentPrivatePlaceBinding = FragmentPrivatePlaceBinding.inflate(layoutInflater)

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
    }

    override fun observeData() {
        when(it) {
            is CalendarState.Uninitialized -> initViews()
            is CalendarState.Loading -> handleLoadingState()
            is CalendarState.Success -> handleSuccessState()
            is CalendarState.Error -> handleErrorState()
        }
    }

    private fun initViews() = with(binding) {
        val dateInfo = getTodayDate()
        selectedYear = dateInfo[0]
        selectedMonth = dateInfo[1] + 1
        selectedDay = dateInfo[2]

        yearTextView.text = "${selectedYear}년"
        monthTextView.text = "${selectedMonth}월"

        lastMonthButton.setOnClickListener {
            val lastMonthDateInfo = getLastOrNextMonthOrDay(selectedYear, selectedMonth-1, selectedDay, true, true)
            selectedYear = lastMonthDateInfo[0]
            selectedMonth = lastMonthDateInfo[1] + 1
            selectedDay = lastMonthDateInfo[2]

            binding.yearTextView.text = "${selectedYear}년"
            binding.monthTextView.text = "${selectedMonth}월"

//            presenter.fetchCalendarRecord(selectedYear, selectedMonth)
        }

        nextMonthDayButton.setOnClickListener {
            val nextMonthDateInfo = getLastOrNextMonthOrDay(selectedYear, selectedMonth-1, selectedDay, false, true)
            selectedYear = nextMonthDateInfo[0]
            selectedMonth = nextMonthDateInfo[1] + 1
            selectedDay = nextMonthDateInfo[2]

            binding.yearTextView.text = "${selectedYear}년"
            binding.monthTextView.text = "${selectedMonth}월"

//            presenter.fetchCalendarRecord(selectedYear, selectedMonth)
        }

        recyclerView.apply {
            layoutManager = GridLayoutManager(context, 7)
            adapter = CalendarAdapter()
        }
    }

    private fun handleLoadingState() = with(binding) {
        progressBar.toVisible()
        errorDescriptionTextView.toGone()
    }

    private fun handleSuccessState() = with(binding) {
        progressBar.toGone()
        errorDescriptionTextView.toGone()

        binding.recyclerView.toVisible()
        binding.errorDescriptionTextView.toGone()

        (binding.recyclerView.adapter as CalendarAdapter).run {
            setDayList(
                getDayList(),
                dayRecordList,
                dayCellClickListener = {

                }
            )
        }
    }

    private fun handleErrorState() = with(binding) {
        progressBar.toVisible()
        errorDescriptionTextView.toVisible()
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