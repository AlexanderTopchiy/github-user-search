package com.wyverx.githubusersearch.remote.usersearch.model

/**
 * This class represents retrofit response data.
 */
data class UserResponse(

    val login: String,
    val avatarUrl: String,
    val reposUrl: String,
    val name: String
)