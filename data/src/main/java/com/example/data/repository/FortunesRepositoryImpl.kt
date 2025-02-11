package com.example.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.data.local.dao.FortunesDao
import com.example.domain.model.Fortunes
import com.example.domain.repository.FortunesRepository
import javax.inject.Inject

class FortunesRepositoryImpl @Inject constructor(
    private val fortunesDao: FortunesDao
) : FortunesRepository {
    override suspend fun insertFortunes(fortunes: Fortunes) {
        fortunesDao.insertFortunes(com.example.data.local.entity.Fortunes(fortunes.fortuneNo,fortunes.fortune))
    }

    override fun getRandomFortune(): LiveData<List<Fortunes>> {
        return fortunesDao.getRandomFortune().map { it.map { Fortunes(it.id, it.fortune) } }
    }
}