package com.wyverx.githubusersearch.remote.usersearch

import com.wyverx.githubusersearch.data.usersearch.model.UserData
import com.wyverx.githubusersearch.data.usersearch.source.UserSearchRemoteDataSource
import com.wyverx.githubusersearch.remote.usersearch.mapper.UserResponseMapper
import com.wyverx.githubusersearch.remote.usersearch.model.UserResponse
import com.wyverx.githubusersearch.remote.usersearch.service.NetworkService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

import kotlin.collections.ArrayList

/**
 * This class represents work with remote source.
 */
class UserSearchRemoteDataSourceImpl : UserSearchRemoteDataSource {

    private val disposable: CompositeDisposable? = null
    private var listFromRemote: MutableList<UserResponse> = ArrayList()
    private var listForData: MutableList<UserData> = ArrayList()
    private val mapRemoteData: UserResponseMapper = UserResponseMapper()


    companion object {
        fun newInstance() : UserSearchRemoteDataSourceImpl {
            return UserSearchRemoteDataSourceImpl()
        }
    }


    override fun getUserDataFromRemote(userLogin: String): MutableList<UserData> {
        disposable?.add(NetworkService
            .getGitHubApi()
            .getUserFromApi(userLogin)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(this::setDataToList)
        )
        disposable?.dispose()
        for (user in listFromRemote) {
            listForData.add(mapRemoteData.mapDataFromUserResponseToUserData(user))
        }
        disposable?.clear()
        return listForData
    }


    private fun setDataToList(list: MutableList<UserResponse>) {
        listFromRemote.addAll(list)
    }
}