package com.wyverx.githubusersearch.remote.usersearch.service

import com.wyverx.githubusersearch.remote.usersearch.model.UserResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * This is interface for search user at GitHub
 */
interface GitHubApi {

    @GET("/search/users?q={login}")
    fun getUserFromApi(@Path("login") login: String) : Observable<MutableList<UserResponse>>
}