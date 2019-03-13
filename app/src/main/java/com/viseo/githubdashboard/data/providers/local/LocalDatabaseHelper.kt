package com.viseo.githubdashboard.data.providers.local

import com.viseo.githubdashboard.data.models.User

class LocalDatabaseHelper(private val githubDao: GithubDao) {

    suspend fun insertUser(user: User) {
        githubDao.insertUser(user)
    }
}