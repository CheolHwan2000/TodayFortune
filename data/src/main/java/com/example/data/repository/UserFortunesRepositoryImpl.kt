package com.example.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.data.local.dao.UserFortunesDao
import com.example.domain.model.UserFortunes
import com.example.domain.repository.UserFortunesRepository
import javax.inject.Inject

class UserFortunesRepositoryImpl @Inject constructor(private val userFortunesDao: UserFortunesDao) :
    UserFortunesRepository {
    override suspend fun insertUserFortune(userFortune: UserFortunes) {
        userFortunesDao.insertUserFortune(com.example.data.local.entity.UserFortunes(0,userFortune.name,userFortune.todayFortune, userFortune.createdDate))
    }

    override fun getUserFortune(name: String): LiveData<List<UserFortunes>> {
        return userFortunesDao.getUserFortune(name).map { list ->
            list.map { entity -> UserFortunes(entity.name, entity.fortune, entity.createdDate) }
        }
    }

    override fun getUserFortuneDuplicate(name: String) : LiveData<List<UserFortunes>> {
        return userFortunesDao.getUserFortune(name).map { list ->
            list.map { entity -> UserFortunes(entity.name, entity.fortune, entity.createdDate) }
        }
    }

}