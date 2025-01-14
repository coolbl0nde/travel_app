package com.example.travelmap.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.travelmap.data.entity.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao() : UserDao
}