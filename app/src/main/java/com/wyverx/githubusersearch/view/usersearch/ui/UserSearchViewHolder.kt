package com.wyverx.githubusersearch.view.usersearch.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.wyverx.githubusersearch.R
import com.wyverx.githubusersearch.domain.usersearch.model.User

class UserSearchViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    private val loginView: TextView? = itemView?.findViewById(R.id.user_login) as? TextView
    private val avatarView: ImageView? = itemView?.findViewById(R.id.user_avatar) as? ImageView


    fun bind(user: User) {
        loginView?.text = user.login
        Picasso.get().load(user.avatarUrl).into(avatarView)
    }
}