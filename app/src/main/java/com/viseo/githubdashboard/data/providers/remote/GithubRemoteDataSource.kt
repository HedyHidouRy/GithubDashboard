package com.viseo.githubdashboard.data.providers.remote

import com.viseo.githubdashboard.data.models.Repository
import com.viseo.githubdashboard.data.models.User
import com.viseo.githubdashboard.data.repositories.GithubRepository

class GithubRemoteDataSource(val githubService: GithubService) : GithubRepository {

    override suspend fun getUser(username: String): User {
        val user = githubService.getUser(username).await()
        return user
    }

    override suspend fun getRepos(username: String): List<Repository> {
        val repos = githubService.getRepos(username).await()
        return repos
    }
}