package com.example.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.entity.Fortunes

@Dao
interface FortunesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFortunes(fortunes: Fortunes)

    @Query("SELECT * FROM fortunes_table ORDER BY RANDOM() LIMIT 1")
    fun getRandomFortune(): LiveData<List<Fortunes>>
}