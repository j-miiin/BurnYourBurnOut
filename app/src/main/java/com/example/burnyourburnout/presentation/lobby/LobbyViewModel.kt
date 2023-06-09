package com.example.burnyourburnout.presentation.lobby

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

internal class LobbyViewModel: BaseViewModel() {

    val scope: CoroutineScope = MainScope()
    override fun fetchData(): Job = scope.launch {

    }


}