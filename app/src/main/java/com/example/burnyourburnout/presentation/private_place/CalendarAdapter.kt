package com.example.burnyourburnout.presentation.private_place

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.burnyourburnout.R
import com.example.burnyourburnout.data.entity.DiaryEntity
import com.example.burnyourburnout.databinding.ViewholderCalendarCellBinding
import com.example.burnyourburnout.presentation.private_place.diary.DiaryDetailDialog

class CalendarAdapter: RecyclerView.Adapter<CalendarAdapter.CalendarCellViewHolder>() {

    lateinit var context: Context
    var dayList = ArrayList<String>()
    var diaryRecordList = ArrayList<DiaryEntity>()
    lateinit var dayCellClickListener: (DiaryEntity) -> Unit

    inner class CalendarCellViewHolder(
        private val binding: ViewholderCalendarCellBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindViews(data: String, position: Int) = with(binding) {
            dayTextView.text = data
            if (position == 0 || position % 7 == 0) dayTextView.setTextColor(Color.parseColor("#E97777"))
            if (data != "") {
                val idx = data.toInt()
                setFeelingState(diaryRecordList[idx-1].feeling)
            }
        }

        fun bindData(data: DiaryEntity) = with(binding) {
            root.setOnClickListener {
                val toDoEntity = showDiaryDetailDialog(data, adapterPosition)
                dayCellClickListener(toDoEntity)
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
        holder.bindViews(dayList[position], position)
        holder.bindData(diaryRecordList[position])
    }

    override fun getItemCount(): Int = dayList.size

    fun setDayList(
        dayList: ArrayList<String>,
        diaryRecordList: ArrayList<DiaryEntity>,
        dayCellClickListener: (DiaryEntity) -> Unit,
    ) {
        this.dayList = dayList
        this.diaryRecordList = diaryRecordList
        this.dayCellClickListener = dayCellClickListener
        notifyDataSetChanged()
    }

    private fun showDiaryDetailDialog(data: DiaryEntity, position: Int): DiaryEntity {
        var updateToDoEntity = data.copy()
        DiaryDetailDialog(context, data) {
            updateToDoEntity = it
            diaryRecordList.set(position, it)
            notifyItemChanged(position)
        }.show()
        return updateToDoEntity
    }
}