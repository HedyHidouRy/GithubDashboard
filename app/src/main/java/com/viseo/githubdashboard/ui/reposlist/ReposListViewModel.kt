package com.viseo.githubdashboard.ui.reposlist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.viseo.githubdashboard.data.models.User
import com.viseo.githubdashboard.data.models.Repository
import com.viseo.githubdashboard.data.repositories.GithubRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ReposListViewModel(val githubRepository: GithubRepository): ViewModel() {

    var reposList = MutableLiveData<List<Repository>>()
    var loading = MutableLiveData<Boolean>()
    var error = MutableLiveData<Boolean>()
    var errorCode = MutableLiveData<Int>()

    init {
        loading.value = false
        errorCode.value = -1
    }

    fun getRepos(user: User) {
        Log.d("Heyyy", user.avatarUrl)

        loading.value = true
        GlobalScope.launch {
            try {
                val reposResponse = githubRepository.getRepos(user.login)
                loading.postValue(false)
                reposList.postValue(reposResponse)
            } catch (e: HttpException) {
                errorCode.postValue(e.code())
                loading.postValue(false)
                error.postValue(true)
            } catch (e: Throwable) {
                errorCode.postValue(-1)
                loading.postValue(false)
                error.postValue(true)
            }
        }
    }


}