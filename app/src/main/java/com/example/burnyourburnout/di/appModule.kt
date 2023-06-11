package com.example.burnyourburnout.di

import com.example.burnyourburnout.data.db.BurnOutDatabase
import com.example.burnyourburnout.data.repository.DiaryRepository
import com.example.burnyourburnout.data.repository.DiaryRepositoryImpl
import com.example.burnyourburnout.presentation.private_place.CalendarContract
import com.example.burnyourburnout.presentation.private_place.CalendarPresenter
import com.example.burnyourburnout.presentation.private_place.PrivatePlaceFragment
import com.example.burnyourburnout.usecase.private_place.AddDiaryUseCase
import com.example.burnyourburnout.usecase.private_place.GetDiaryUseCase
import com.example.burnyourburnout.usecase.private_place.UpdateDiaryUseCase
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

internal val appModule = module {

    single { Dispatchers.Main }
    single { Dispatchers.IO }

    single { BurnOutDatabase.build(androidApplication()) }
    single { get<BurnOutDatabase>().diaryDao() }

    // Repository
    single<DiaryRepository> { DiaryRepositoryImpl(get(), get()) }

    // UseCase
    factory { GetDiaryUseCase(get()) }
    factory { AddDiaryUseCase(get()) }
    factory { UpdateDiaryUseCase(get()) }

    // Presentation
    scope<PrivatePlaceFragment> {
        scoped<CalendarContract.Presenter> { CalendarPresenter(get(), get(), get(), get()) }
    }
}