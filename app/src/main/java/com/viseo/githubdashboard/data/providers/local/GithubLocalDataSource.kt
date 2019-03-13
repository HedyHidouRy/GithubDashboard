package com.viseo.githubdashboard.data.providers.local

import com.viseo.githubdashboard.data.models.Repository
import com.viseo.githubdashboard.data.models.User
import com.viseo.githubdashboard.data.repositories.GithubRepository

class GithubLocalDataSource(private val githubDao: GithubDao): GithubRepository {

    override suspend fun getUser(username: String): User {
        return githubDao.getUser(username)
    }

    override suspend fun getRepos(username: String): List<Repository> {
        // Not implemented, not used yet
        return ArrayList<Repository>()
    }

}