package com.example.burnyourburnout.presentation.private_place

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.burnyourburnout.databinding.FragmentPrivatePlaceBinding
import com.example.burnyourburnout.presentation.BaseFragment

internal class PrivatePlaceFragment : BaseFragment<CalendarViewModel, FragmentPrivatePlaceBinding>() {

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
}