package com.viseo.githubdashboard.data.repositories

import com.viseo.githubdashboard.data.models.Repository
import com.viseo.githubdashboard.data.models.User

interface GithubRepository {
    /**
     * @param username the github passed username
     * @return the user response after some suspend
     */
    suspend fun getUser(username: String): User

    /**
     * @param username the github passed username
     * @return the repos list response after some suspend
     */
    suspend fun getRepos(username: String): List<Repository>


}