package com.example.domain.usecase

import com.example.domain.model.Fortunes
import com.example.domain.repository.FortunesRepository
import javax.inject.Inject

class RandomFortuneUseCase @Inject constructor(
    private val repository: FortunesRepository
) {
    suspend operator fun invoke(): Fortunes {
        return repository.getRandomFortune()
    }
}