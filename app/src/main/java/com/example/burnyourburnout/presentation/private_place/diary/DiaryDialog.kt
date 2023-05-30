package com.example.burnyourburnout.presentation.private_place.diary

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.example.burnyourburnout.databinding.DialogDiaryBinding

class DiaryDialog(
    context: Context,
): Dialog(context) {

    private lateinit var binding: DialogDiaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun initViews() = with(binding) {

    }
}