package com.example.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.data.local.dao.UserFortunesDao
import com.example.data.mapper.FortuneMapper
import com.example.domain.model.UserFortunes
import com.example.domain.repository.UserFortunesRepository
import javax.inject.Inject

class UserFortunesRepositoryImpl @Inject constructor(private val userFortunesDao: UserFortunesDao) :
    UserFortunesRepository {
    override suspend fun insertUserFortune(userFortune: UserFortunes) {
        userFortunesDao.insertUserFortune(
            com.example.data.local.entity.UserFortunes(
                0,
                userFortune.name,
                userFortune.todayFortune,
                userFortune.createdDate
            )
        )
    }

    override suspend fun getUserFortune(name: String): List<UserFortunes> {
        return userFortunesDao.getUserFortune(name)
            .map { UserFortunes(it.name, it.fortune, it.createdDate) }
    }

    override suspend fun getUserFortuneDuplicate(name: String): List<UserFortunes> {
        return userFortunesDao.getSameUserFortune(name)
            .map { UserFortunes(it.name, it.fortune, it.createdDate) }
    }
}

