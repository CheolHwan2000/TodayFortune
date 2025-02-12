package com.example.domain.repository

import androidx.lifecycle.LiveData
import com.example.domain.model.Fortunes

interface FortunesRepository {
//    suspend fun insertFortunes(fortunes: Fortunes): Result<Unit>
    fun getRandomFortune(): LiveData<List<Fortunes>>
    suspend fun fetchFortunes()
}

// 이름 중복을 막아야 하는데.. 이건 그냥 쿼리문을 써서 막는다고 치고, 운세를