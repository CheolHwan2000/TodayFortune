package com.example.domain.usecase

import com.example.domain.model.Fortunes
import com.example.domain.repository.FortunesRepository
import javax.inject.Inject

class InitFortunesUseCase @Inject constructor(val repository: FortunesRepository) {
    suspend fun invoke(fortunes: Fortunes){
        repository.insertFortunes(fortunes)
    }
}