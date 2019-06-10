package com.wyverx.githubusersearch.presentation.usersearch

import android.widget.SearchView
import com.wyverx.githubusersearch.domain.usersearch.interactor.UserSearchInteractor
import com.wyverx.githubusersearch.domain.usersearch.model.User
import com.wyverx.githubusersearch.view.usersearch.ui.UserSearchFragment
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

/**
 * This class interact with domain and view layers.
 */
class UserSearchPresenter (
    private val userSearchInteractor: UserSearchInteractor,
    private val userSearchView: UserSearchView) {


    companion object {
        fun newInstance(userSearchInteractor: UserSearchInteractor,
                        userSearchView: UserSearchView): UserSearchPresenter {
            return UserSearchPresenter(userSearchInteractor, userSearchView)
        }
    }


    fun loadCallback(text: String) {
        val userList: MutableList<User> = userSearchInteractor.fetchUsers(text)
        userSearchView.showResult(userList)
    }
}