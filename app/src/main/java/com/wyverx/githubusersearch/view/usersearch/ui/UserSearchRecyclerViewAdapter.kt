package com.wyverx.githubusersearch.view.usersearch.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wyverx.githubusersearch.R
import com.wyverx.githubusersearch.domain.usersearch.model.User

/**
 * This is adapter for search results.
 */
class UserSearchRecyclerViewAdapter : RecyclerView.Adapter<UserSearchViewHolder>() {

    private val userList: MutableList<User> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserSearchViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_user_search_list_item, parent, false)
        return UserSearchViewHolder(view)
    }


    override fun onBindViewHolder(holder: UserSearchViewHolder, position: Int) {
        val item: User = userList.get(position)
        holder.bind(item)
    }


    override fun getItemCount(): Int {
        return userList.size
    }


    fun updateDataSet(userList: MutableList<User>) {
        this.userList.clear()
        this.userList.addAll(userList)
        notifyDataSetChanged()
    }
}