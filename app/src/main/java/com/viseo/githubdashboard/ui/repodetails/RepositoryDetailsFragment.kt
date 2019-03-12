package com.viseo.githubdashboard.ui.repodetails


import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import com.viseo.githubdashboard.BR
import com.viseo.githubdashboard.R
import com.viseo.githubdashboard.core.base.BaseFragment
import com.viseo.githubdashboard.data.models.Repository
import com.viseo.githubdashboard.data.models.User
import com.viseo.githubdashboard.ui.reposlist.ReposListFragmentArgs
import org.koin.android.viewmodel.ext.android.viewModel


class RepositoryDetailsFragment : BaseFragment() {

    private val repoViewModel: RepositoryDetailsViewModel by viewModel()

    private lateinit var user: User
    private lateinit var repository: Repository

    override fun layoutId(): Int = R.layout.fragment_repository_details

    override fun viewModelBinding(): ViewModel = repoViewModel

    override fun bindingVariable(): Int = BR.repoViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            user = RepositoryDetailsFragmentArgs.fromBundle(it).user
            repository = RepositoryDetailsFragmentArgs.fromBundle(it).repository
            repoViewModel.updateData(user, repository)
        }
    }
}
