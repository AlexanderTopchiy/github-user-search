package com.wyverx.githubusersearch.data.usersearch.repository

import com.wyverx.githubusersearch.data.usersearch.model.UserData
import com.wyverx.githubusersearch.data.usersearch.mapper.UserDataMapper
import com.wyverx.githubusersearch.data.usersearch.source.UserSearchRemoteDataSource
import com.wyverx.githubusersearch.domain.usersearch.model.User
import com.wyverx.githubusersearch.domain.usersearch.repository.UserSearchRepository

/**
 * This class implements interface from domain layer.
 */
class UserSearchRepositoryImpl : UserSearchRepository {

    private val userDataList: MutableList<UserData> = ArrayList()
    private val userList: MutableList<User> = ArrayList()
    private val mapData: UserDataMapper = UserDataMapper()


    fun getUserData(dataFromRemote: UserSearchRemoteDataSource) {
        userDataList.addAll(dataFromRemote.getUserDataFromRemote())
    }


    override fun getUsers(): MutableList<User> {
        for (userData in userDataList) {
            userList.add(mapData.mapFromUserDataToUser(userData))
        }
        return userList
    }
}