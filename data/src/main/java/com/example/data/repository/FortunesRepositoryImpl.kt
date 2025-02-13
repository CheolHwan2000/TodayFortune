package com.example.data.repository

import com.example.data.dto.DummyDto.fortuneData
import com.example.data.local.dao.FortunesDao
import com.example.data.mapper.FortuneMapper
import com.example.domain.model.Fortunes
import com.example.domain.repository.FortunesRepository
import javax.inject.Inject

class FortunesRepositoryImpl @Inject constructor(
    private val fortunesDao: FortunesDao
) : FortunesRepository {

    override suspend fun getRandomFortune(): Fortunes {
        return FortuneMapper.entityToModel(fortunesDao.getRandomFortune())
    }

    override suspend fun fetchFortunes() {
        val fortunes = fortuneData.map { FortuneMapper.dtoToEntity(it) }
        fortunesDao.insertFortunes(fortunes)
    }
}