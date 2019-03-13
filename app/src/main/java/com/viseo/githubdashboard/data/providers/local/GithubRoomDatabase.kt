package com.viseo.githubdashboard.data.providers.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.viseo.githubdashboard.data.models.User

@Database(entities = arrayOf(User::class), version = 1, exportSchema = false)
public abstract class GithubRoomDatabase : RoomDatabase() {

    abstract fun githubDao(): GithubDao

}