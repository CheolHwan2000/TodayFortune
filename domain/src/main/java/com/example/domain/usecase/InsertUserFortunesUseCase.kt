package com.example.domain.usecase

import com.example.domain.model.UserFortunes
import com.example.domain.repository.UserFortunesRepository
import javax.inject.Inject

class InsertUserFortunesUseCase @Inject constructor(val repository: UserFortunesRepository) {
    suspend operator fun invoke(fortunes: UserFortunes) {
        repository.insertUserFortune(fortunes)

    }
}