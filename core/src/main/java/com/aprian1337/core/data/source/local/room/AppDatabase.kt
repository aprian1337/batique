package com.aprian1337.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [com.aprian1337.core.data.source.local.entity.BatikEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun batikDao(): BatikDao
}