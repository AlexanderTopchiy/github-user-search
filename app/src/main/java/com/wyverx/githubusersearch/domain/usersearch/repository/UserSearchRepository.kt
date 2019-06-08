package com.wyverx.githubusersearch.domain.usersearch.repository

import com.wyverx.githubusersearch.domain.usersearch.model.User

/**
 * This is interface for data layer.
 */
interface UserSearchRepository {

    fun getUsers(userName: String): MutableList<User>
}