package com.example.burnyourburnout.presentation.private_place

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.burnyourburnout.R
import com.example.burnyourburnout.data.entity.DiaryEntity
import com.example.burnyourburnout.databinding.ViewholderCalendarCellBinding

class CalendarAdapter: RecyclerView.Adapter<CalendarAdapter.CalendarCellViewHolder>() {

    var dayList = ArrayList<String>()
    var dayRecordList = ArrayList<DiaryEntity>()
    lateinit var dayCellClickListener: (DiaryEntity) -> Unit

    inner class CalendarCellViewHolder(
        private val binding: ViewholderCalendarCellBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(data: String, position: Int) = with(binding) {
            dayTextView.text = data
            if (position == 0 || position % 7 == 0) dayTextView.setTextColor(Color.parseColor("#E97777"))
            if (data != "") {
                val idx = data.toInt()
                setFeelingState(dayRecordList[idx-1].feeling)
                Log.d("feeling", dayRecordList[idx-1].feeling.toString())
            }
        }

        private fun setFeelingState(feeling: Int) {
            when (feeling) {
                1 -> binding.recordButton.setImageResource(R.drawable.ic_perfect)
                2 -> binding.recordButton.setImageResource(R.drawable.ic_good)
                3 -> binding.recordButton.setImageResource(R.drawable.ic_soso)
                4 -> binding.recordButton.setImageResource(R.drawable.ic_bad)
                5 -> binding.recordButton.setImageResource(R.drawable.ic_tired)
                6 -> binding.recordButton.setImageResource(R.drawable.ic_sick)
                0 -> binding.recordButton.setImageResource(R.drawable.ic_none)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarCellViewHolder {
        val view = ViewholderCalendarCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CalendarCellViewHolder(view)
    }

    override fun onBindViewHolder(holder: CalendarCellViewHolder, position: Int) {
        holder.bindData(dayList[position], position)
    }

    override fun getItemCount(): Int = dayList.size

    fun setDayList(
        dayList: ArrayList<String>,
        diaryRecordList: ArrayList<DiaryEntity>,
        dayCellClickListener: (DiaryEntity) -> Unit,
    ) {
        this.dayList = dayList
        this.dayRecordList = diaryRecordList
        this.dayCellClickListener = dayCellClickListener
        notifyDataSetChanged()
    }
}