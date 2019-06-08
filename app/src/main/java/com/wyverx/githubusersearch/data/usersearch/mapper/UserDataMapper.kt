package com.wyverx.githubusersearch.data.usersearch.mapper

import com.wyverx.githubusersearch.data.usersearch.model.UserData
import com.wyverx.githubusersearch.domain.usersearch.model.User

/**
 * This class map from data models to domain layer models.
 */
class UserDataMapper {

    fun mapFromUserDataToUser(from: UserData) : User {
        return User(
            from.login,
            from.avatarUrl,
            from.reposUrl,
            from.name
        )
    }
}