package com.viseo.githubdashboard.ui.usersearch

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.viseo.githubdashboard.BR
import com.viseo.githubdashboard.R
import com.viseo.githubdashboard.core.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class SearchUserFragment : BaseFragment() {

    private val viewModel: SearchUserViewModel by viewModel()

    override fun layoutId(): Int = R.layout.fragment_search_user

    override fun viewModelBinding(): ViewModel = viewModel

    override fun bindingVariable(): Int = BR.viewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Listen for response and redirection
        viewModel.successResponse.observe(this, Observer {
            if (Navigation.findNavController(view).currentDestination?.id == R.id.searchUserFragment) {
                val directions =
                    SearchUserFragmentDirections.actionSearchUserFragmentToReposListFragment(it)
                Navigation.findNavController(view).navigate(directions)
            }
        })
    }

    override fun onStop() {
        super.onStop()
        viewModel.successResponse.removeObservers(this)
    }
}
