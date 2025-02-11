package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_fortunes_table")
data class UserFortunes(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val fortune: String
)
