package com.wyverx.githubusersearch.domain.usersearch.interactor

import com.wyverx.githubusersearch.domain.usersearch.model.User
import com.wyverx.githubusersearch.domain.usersearch.repository.UserSearchRepository

/**
 * This class represents user search use case.
 */
class UserSearchInteractor (private val userSearchRepository: UserSearchRepository) {

    companion object {
        fun newInstance(userSearchRepository: UserSearchRepository): UserSearchInteractor {
            return UserSearchInteractor(userSearchRepository)
        }
    }


    fun fetchUsers(userLogin: String) : MutableList<User> = userSearchRepository.getUsers(userLogin)
}