package com.viseo.githubdashboard

import android.app.Application
import com.viseo.githubdashboard.di.appModule
import com.viseo.githubdashboard.di.localDatasourceModule
import com.viseo.githubdashboard.di.remoteDatasourceModule
import org.koin.android.ext.android.startKoin

class GithubDashboardApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initiateDI()
    }

    /**
     * Initiate the DI part of the application
     */
    private fun initiateDI() {
        startKoin(this, listOf(remoteDatasourceModule, localDatasourceModule, appModule))
    }
}