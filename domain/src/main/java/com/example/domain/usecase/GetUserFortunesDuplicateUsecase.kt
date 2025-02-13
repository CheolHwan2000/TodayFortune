package com.example.domain.usecase

import com.example.domain.model.UserFortunes
import com.example.domain.repository.UserFortunesRepository
import javax.inject.Inject

class GetUserFortunesDuplicateUsecase @Inject constructor(val repository: UserFortunesRepository) {
    suspend operator fun invoke(name: String): List<UserFortunes> {
        return repository.getUserFortuneDuplicate(name)
    }
}