package com.wyverx.githubusersearch.domain.usersearch.interactor

import com.wyverx.githubusersearch.domain.usersearch.model.User
import com.wyverx.githubusersearch.domain.usersearch.repository.UserSearchRepository

class UserSearchInteractor (private val userSearchRepository: UserSearchRepository) {

    fun fetchUsers(userName: String) : MutableList<User> = userSearchRepository.getUsers(userName)
}