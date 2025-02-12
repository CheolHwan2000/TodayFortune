package com.example.domain.repository

import androidx.lifecycle.LiveData
import com.example.domain.model.UserFortunes

interface UserFortunesRepository {
    suspend fun insertUserFortune(userFortune: UserFortunes)
    fun getUserFortune(name : String): LiveData<List<UserFortunes>>
    fun getUserFortuneDuplicate(name : String): LiveData<List<UserFortunes>>
}