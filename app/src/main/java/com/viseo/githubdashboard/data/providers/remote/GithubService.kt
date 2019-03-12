package com.viseo.githubdashboard.data.providers.remote

import com.viseo.githubdashboard.data.models.Repository
import com.viseo.githubdashboard.data.models.User
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("/users/{username}")
    fun getUser(@Path("username") username: String): Deferred<User>

    @GET("/users/{username}/repos")
    fun getRepos(@Path("username") username: String): Deferred<List<Repository>>

}