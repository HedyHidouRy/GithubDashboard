package com.viseo.githubdashboard.ui.usersearch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.viseo.githubdashboard.data.models.User
import com.viseo.githubdashboard.data.providers.local.LocalDatabaseHelper
import com.viseo.githubdashboard.data.repositories.GithubRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.HttpException

class SearchUserViewModel(
    private val githubRemote: GithubRepository,
    private val githubLocal: GithubRepository,
    val localDatabaseHelper: LocalDatabaseHelper
) : ViewModel() {

    var successResponse = MutableLiveData<User>()
    var loading = MutableLiveData<Boolean>()
    var error = MutableLiveData<Boolean>()
    var errorCode = MutableLiveData<Int>()

    init {
        loading.value = false
        errorCode.value = -1
    }

    var searchContent: String? = null

    fun onSearch() {
        error.value = false
        searchContent?.let {
            GlobalScope.launch {
                val user = githubLocal.getUser(it)
                if (user != null) {
                    successResponse.postValue(user)
                } else {
                    try {
                        loading.postValue(true)
                        val response = githubRemote.getUser(it)
                        loading.postValue(false)
                        localDatabaseHelper.insertUser(user = response)
                        successResponse.postValue(response)
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
    }

    fun onContentChanged(content: CharSequence) {
        this.searchContent = content.toString()

    }

}