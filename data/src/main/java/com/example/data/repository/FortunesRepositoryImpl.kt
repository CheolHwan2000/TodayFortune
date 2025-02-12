package com.example.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.data.dto.DummyDto.fortuneData
import com.example.data.local.dao.FortunesDao
import com.example.data.mapper.FortuneMapper
import com.example.domain.model.Fortunes
import com.example.domain.repository.FortunesRepository
import javax.inject.Inject

class FortunesRepositoryImpl @Inject constructor(
    private val fortunesDao: FortunesDao
) : FortunesRepository {

    override fun getRandomFortune(): LiveData<List<Fortunes>> {
        return fortunesDao.getRandomFortune().map { it.map { Fortunes(it.id, it.fortune) } }
    }

    override suspend fun fetchFortunes(){
        val fortunes = fortuneData.map { FortuneMapper.dtoToEntity(it) }
         fortunesDao.insertFortunes(fortunes)
    }
}