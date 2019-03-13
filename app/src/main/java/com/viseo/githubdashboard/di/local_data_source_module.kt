package com.viseo.githubdashboard.di

import androidx.room.Room
import com.viseo.githubdashboard.data.providers.local.GithubRoomDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

val localDatasourceModule = module(definition = {
    single {
        Room.databaseBuilder(androidApplication(), GithubRoomDatabase::class.java, "github_database")
            .build()
    }
})