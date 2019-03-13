package com.viseo.githubdashboard.data.providers.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.viseo.githubdashboard.data.models.User


const val USERS_TABLE = "users"

@Dao
interface GithubDao {

    @Query("SELECT * FROM $USERS_TABLE WHERE login LIKE :login LIMIT 1")
    fun getUser(login: String): User

    @Insert(onConflict = REPLACE)
    fun insertUser(user: User): Long

}