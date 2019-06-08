package com.wyverx.githubusersearch.data.usersearch.source

import com.wyverx.githubusersearch.data.usersearch.model.UserData

/**
 * This is source interface for data layer.
 */
interface UserSearchRemoteDataSource {

    fun getUserDataFromRemote() : MutableList<UserData>
}