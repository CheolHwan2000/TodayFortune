package com.example.data.remote

import retrofit2.http.GET

interface FortuneApiService {
    @GET("api/fortunes")
    suspend fun getFortunes() : List<FortuneResponse>
}