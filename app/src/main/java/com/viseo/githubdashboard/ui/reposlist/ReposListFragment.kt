package com.viseo.githubdashboard.ui.reposlist


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.viseo.githubdashboard.BR
import com.viseo.githubdashboard.R
import com.viseo.githubdashboard.core.base.BaseFragment
import com.viseo.githubdashboard.data.models.User
import com.viseo.githubdashboard.ui.reposlist.adapters.ReposListAdapter
import com.viseo.githubdashboard.ui.usersearch.SearchUserFragmentDirections
import kotlinx.android.synthetic.main.fragment_repos_list.*
import org.koin.android.viewmodel.ext.android.viewModel


class ReposListFragment : BaseFragment() {

    private val reposViewModel: ReposListViewModel by viewModel()

    private lateinit var user: User

    override fun layoutId(): Int = R.layout.fragment_repos_list

    override fun viewModelBinding(): ViewModel = reposViewModel

    override fun bindingVariable(): Int = BR.reposViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            user = ReposListFragmentArgs.fromBundle(it).user
            reposViewModel.getRepos(user = user)
            prepareRecyclerView()
        }
    }

    private fun prepareRecyclerView() {
        val adapter = ReposListAdapter(context!!, user)
        reposRecycler.layoutManager = LinearLayoutManager(context)
        reposRecycler.adapter = adapter
        adapter.onItemClick = {
            val directions =
                ReposListFragmentDirections.actionReposListFragmentToRepositoryDetailsFragment(user, it)
            Navigation.findNavController(view!!).navigate(directions)
        }
    }
}
