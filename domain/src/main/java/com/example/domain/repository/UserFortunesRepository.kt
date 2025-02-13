package com.example.domain.repository

import com.example.domain.model.UserFortunes

interface UserFortunesRepository {
    suspend fun insertUserFortune(userFortune: UserFortunes)
    suspend fun getUserFortune(name: String): List<UserFortunes>
    suspend fun getUserFortuneDuplicate(name: String): List<UserFortunes>
}