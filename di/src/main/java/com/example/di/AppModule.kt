package com.example.di

import android.content.Context
import androidx.room.Room
import com.example.data.local.dao.FortunesDao
import com.example.data.local.dao.UserFortunesDao
import com.example.data.local.database.MainDatabase
import com.example.data.remote.FortuneApiService
import com.example.data.repository.FortunesRepositoryImpl
import com.example.data.repository.UserFortunesRepositoryImpl
import com.example.domain.repository.FortunesRepository
import com.example.domain.repository.UserFortunesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // RoomDB 제공
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MainDatabase {
        return Room.databaseBuilder(
            context,
            MainDatabase::class.java,
            "fortune_database"
        ).build()
    }

    @Provides
    fun provideFortuneDao(database: MainDatabase): FortunesDao {
        return database.fortunesDao()
    }

    @Provides
    @Singleton
    fun provideFortunesRepository(fortunesDao: FortunesDao): FortunesRepository {
        return FortunesRepositoryImpl(fortunesDao)
    }

    @Provides
    fun provideUserFortuneDao(database: MainDatabase): UserFortunesDao {
        return database.userFortunesDao()
    }

    @Provides
    @Singleton
    fun provideUserFortunesRepository(userFortunesDao: UserFortunesDao): UserFortunesRepository {
        return UserFortunesRepositoryImpl(userFortunesDao)
    }

    // Retrofit API 제공
    @Provides
    @Singleton
    fun provideFortuneApiService(): FortuneApiService {
        return Retrofit.Builder()
            .baseUrl("https://api.example.com/") // 실제 API 엔드포인트 입력
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FortuneApiService::class.java)
    }
}