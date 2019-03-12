package com.viseo.githubdashboard.ui.repodetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.viseo.githubdashboard.data.models.Repository
import com.viseo.githubdashboard.data.models.User

class RepositoryDetailsViewModel: ViewModel() {
    var user = MutableLiveData<User>()
    var repository = MutableLiveData<Repository>()

    fun updateData(user: User, repository: Repository) {
        this.user.postValue(user)
        this.repository.postValue(repository)
    }
}