package com.example.domain.usecase

import androidx.lifecycle.LiveData
import com.example.domain.model.Fortunes
import com.example.domain.repository.FortunesRepository
import javax.inject.Inject

class RandomFortuneUseCase @Inject constructor(
    private val repository: FortunesRepository
) {
    operator fun invoke():LiveData<List<Fortunes>>{
        return repository.getRandomFortune()
    }
}