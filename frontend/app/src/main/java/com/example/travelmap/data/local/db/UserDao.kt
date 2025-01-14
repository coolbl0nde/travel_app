package com.example.travelmap.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.travelmap.data.entity.UserEntity

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM users WHERE id = :userId")
    suspend fun getUserById(userId: String): UserEntity?

    @Query("DELETE FROM users")
    suspend fun deleteUsers()

}
