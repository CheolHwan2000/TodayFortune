package com.example.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.entity.UserFortunes

@Dao
interface UserFortunesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserFortune(userFortune: UserFortunes)

    @Query("SELECT * FROM user_fortunes_table WHERE name = :name and createdDate = date('now')")
    fun getSameUserFortune(name : String) : LiveData<List<UserFortunes>>

    @Query("SELECT * FROM user_fortunes_table WHERE name = :name")
    fun getUserFortune(name : String): LiveData<List<UserFortunes>>
}