package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fortunes_table")
data class Fortunes(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val fortune: String
)