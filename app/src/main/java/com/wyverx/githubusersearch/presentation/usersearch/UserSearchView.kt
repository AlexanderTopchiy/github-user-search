package com.wyverx.githubusersearch.presentation.usersearch

import com.wyverx.githubusersearch.domain.usersearch.model.User

interface UserSearchView {

    fun showResult(userList: MutableList<User>)
}