package com.viseo.githubdashboard.ui.usersearch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.viseo.githubdashboard.data.models.User
import com.viseo.githubdashboard.data.repositories.GithubRepository
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.HttpException

class SearchUserViewModel(val githubRepository: GithubRepository) : ViewModel() {

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
            loading.value = true
            GlobalScope.launch {
                try {
                    val response = githubRepository.getUser(it)
                    loading.postValue(false)
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

    fun onContentChanged(content: CharSequence) {
        this.searchContent = content.toString()

    }

}