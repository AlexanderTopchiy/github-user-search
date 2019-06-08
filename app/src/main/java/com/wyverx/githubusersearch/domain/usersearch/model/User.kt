package com.wyverx.githubusersearch.domain.usersearch.model

/**
 * This data class represents domain layer models.
 */
data class User (

    val login: String,
    val avatarUrl: String,
    val reposUrl: String,
    val name: String
)