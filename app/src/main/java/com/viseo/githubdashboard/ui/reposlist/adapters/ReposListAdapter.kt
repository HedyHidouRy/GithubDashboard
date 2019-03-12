package com.viseo.githubdashboard.ui.reposlist.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.viseo.githubdashboard.R
import com.viseo.githubdashboard.data.models.Repository
import com.viseo.githubdashboard.data.models.User
import com.viseo.githubdashboard.utils.BindableAdapter
import kotlinx.android.synthetic.main.repository_list_item.view.*
import kotlinx.android.synthetic.main.user_item.view.*

class ReposListAdapter(private val context: Context, private val user: User) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    BindableAdapter<List<Repository>> {

    var onItemClick: ((Repository) -> Unit)? = null
    private var reposList = ArrayList<Repository>()

    override fun setData(data: List<Repository>) {
        reposList.clear()
        reposList.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            USER_PROFILE_VIEW_TYPE -> {
                val inflatedView = LayoutInflater.from(context).inflate(R.layout.user_item, parent, false)
                UserViewHolder(inflatedView)
            }
            else -> {
                val inflatedView = LayoutInflater.from(context).inflate(R.layout.repository_list_item, parent, false)
                RepoViewHolder(inflatedView)
            }
        }
    }

    override fun getItemCount(): Int = reposList.size + 1

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return USER_PROFILE_VIEW_TYPE
        }
        return REPO_ITEM_VIEW_TYPE
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        when(position) {
            0 -> {
                (viewHolder as UserViewHolder).bindItem(user)
            }
            else -> {
                (viewHolder as RepoViewHolder).bindItem(reposList[position - 1])
            }
        }
    }

    inner class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(item: Repository) {
            itemView.setOnClickListener {
                onItemClick?.invoke(item)
            }
            itemView.repoName.text = item.name
        }

    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(item: User) {
            itemView.username.text = item.login
            Picasso.with(itemView.context)
                .load(item.avatarUrl)
                .into(itemView.userAvatar)
        }

    }

    companion object {
        private const val USER_PROFILE_VIEW_TYPE = 1
        private const val REPO_ITEM_VIEW_TYPE = 2
    }

}