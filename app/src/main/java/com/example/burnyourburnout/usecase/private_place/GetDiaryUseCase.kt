package com.example.burnyourburnout.usecase.private_place

import com.example.burnyourburnout.data.entity.DiaryEntity
import com.example.burnyourburnout.data.repository.DiaryRepository

class GetDiaryUseCase(private val diaryRepository: DiaryRepository) {

    suspend operator fun invoke(id: String): DiaryEntity? = diaryRepository.getDiary(id)
}