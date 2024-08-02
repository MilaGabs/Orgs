package com.example.orgs.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.orgs.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert
    suspend fun save(user: User)

    @Query("SELECT * FROM User WHERE id = :userId AND password = :password")
    suspend fun authenticateUser(userId: String, password: String): User?

    @Query("SELECT * FROM User WHERE id = :userId")
    fun getUser(userId: String): Flow<User>
}