package com.example.burnyourburnout

interface BaseView<PresenterT: BasePresenter> {

    val presenter: PresenterT
}