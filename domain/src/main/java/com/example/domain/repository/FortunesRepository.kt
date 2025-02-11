package com.example.domain.repository

import androidx.lifecycle.LiveData
import com.example.domain.model.Fortunes

interface FortunesRepository {
    suspend fun insertFortunes(fortunes: Fortunes)
    fun getRandomFortune(): LiveData<List<Fortunes>>
}