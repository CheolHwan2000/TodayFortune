package com.example.domain.usecase

import com.example.domain.repository.FortunesRepository
import javax.inject.Inject

class FetchFortunesUseCase @Inject constructor(val repository: FortunesRepository) {
    suspend operator fun invoke() {
        repository.fetchFortunes()
    }
}
