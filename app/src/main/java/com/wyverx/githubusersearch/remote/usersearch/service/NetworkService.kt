package com.wyverx.githubusersearch.remote.usersearch.service

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * This class represents Retrofit instance.
 */
class NetworkService {

    companion object {
        fun getGitHubApi(): GitHubApi {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

            return retrofit.create(GitHubApi::class.java)
        }
    }
}