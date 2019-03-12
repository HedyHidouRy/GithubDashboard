package com.viseo.githubdashboard.di

import com.viseo.githubdashboard.data.providers.remote.GithubRemoteDataSource
import com.viseo.githubdashboard.data.repositories.GithubRepository
import com.viseo.githubdashboard.ui.repodetails.RepositoryDetailsViewModel
import com.viseo.githubdashboard.ui.reposlist.ReposListViewModel
import com.viseo.githubdashboard.ui.usersearch.SearchUserViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

const val REMOTE_DATA_SOURCE = "remote"
const val LOCAL_DATA_SOURCE = "local"

val appModule = module {
    viewModel { SearchUserViewModel(get(name = REMOTE_DATA_SOURCE)) }
    viewModel { ReposListViewModel(get(name = REMOTE_DATA_SOURCE)) }
    viewModel { RepositoryDetailsViewModel() }

    single<GithubRepository>(REMOTE_DATA_SOURCE) { GithubRemoteDataSource(get()) }
    // single<GithubRepository>("test") { ServiceImpl() }
}