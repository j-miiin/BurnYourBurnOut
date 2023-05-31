package com.example.burnyourburnout.presentation.private_place.diary

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.burnyourburnout.data.entity.DiaryEntity
import com.example.burnyourburnout.databinding.DialogDiaryBinding

class DiaryDialog(
    context: Context,
    private val diaryEntity: DiaryEntity,
    private val okCallBack: (DiaryEntity) -> Unit
): Dialog(context) {

    private lateinit var binding: DialogDiaryBinding

    private var editMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogDiaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() = with(binding) {
        setCancelable(true)

        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        yearTextView.text = "${diaryEntity.year}년"
        dateTextView.text = "${diaryEntity.month}월 ${diaryEntity.day}일"
        contentEditText.setText(diaryEntity.content)

        editButton.setOnClickListener {
            editMode = !editMode

            if (editMode) {
                editButton.text = "SAVE"
                contentEditText.isFocusableInTouchMode = true
            } else {
                hideKeyboard()
                contentEditText.isFocusableInTouchMode = false
                contentEditText.isFocusable = false
            }
        }

        closeButton.setOnClickListener {
            if (editMode) {
                Toast.makeText(context, "수정을 완료해주세요!", Toast.LENGTH_SHORT).show()
            } else {
                okCallBack(
                    DiaryEntity(
                        id = diaryEntity.id,
                        year = diaryEntity.year,
                        month = diaryEntity.month,
                        day = diaryEntity.day,
                        content = contentEditText.text.toString()
                    )
                )
                dismiss()
            }
        }
    }

    private fun hideKeyboard() {
        val inputMethodManager = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
}