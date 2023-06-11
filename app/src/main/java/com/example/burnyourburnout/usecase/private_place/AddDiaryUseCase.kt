package com.example.burnyourburnout.usecase.private_place

import com.example.burnyourburnout.data.entity.DiaryEntity
import com.example.burnyourburnout.data.repository.DiaryRepository

class AddDiaryUseCase(private val diaryRepository: DiaryRepository) {

    suspend operator fun invoke(diaryEntity: DiaryEntity) = diaryRepository.addDiary(diaryEntity)
}