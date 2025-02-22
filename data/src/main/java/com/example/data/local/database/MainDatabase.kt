package com.example.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.data.local.dao.FortunesDao
import com.example.data.local.dao.UserFortunesDao
import com.example.data.local.entity.Fortunes
import com.example.data.local.entity.UserFortunes

@Database(entities = [Fortunes::class, UserFortunes::class], version = 2, exportSchema = false)
abstract class MainDatabase : RoomDatabase() {
    abstract fun fortunesDao(): FortunesDao
    abstract fun userFortunesDao(): UserFortunesDao


    companion object {
        private var INSTANCE: MainDatabase? = null
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                // `UserFortunes` 테이블에 `createdDate` 필드 추가
//                db.execSQL("ALTER TABLE UserFortunes ADD COLUMN createdDate TEXT NOT NULL DEFAULT ''")
            }
        }

        fun getDatabase(context: Context): MainDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    MainDatabase::class.java,
                    "fortune_database"
                )
                    .addMigrations(MIGRATION_1_2) // 추가된 마이그레이션 적용
                    .build()
            }
            return INSTANCE as MainDatabase
        }
    }
}