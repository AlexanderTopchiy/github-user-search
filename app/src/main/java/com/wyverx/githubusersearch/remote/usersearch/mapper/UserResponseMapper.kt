package com.wyverx.githubusersearch.remote.usersearch.mapper

import com.wyverx.githubusersearch.data.usersearch.model.UserData
import com.wyverx.githubusersearch.remote.usersearch.model.UserResponse

/**
 * This class map from response models to data layer models.
 */
class UserResponseMapper {

    fun mapDataFromUserResponseToUserData(from: UserResponse) : UserData {
        return UserData(
            from.login,
            from.avatarUrl,
            from.reposUrl,
            from.name
        )
    }
}