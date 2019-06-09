package com.wyverx.githubusersearch.presentation.usersearch

import android.widget.SearchView
import com.wyverx.githubusersearch.domain.usersearch.interactor.UserSearchInteractor
import com.wyverx.githubusersearch.domain.usersearch.model.User
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

    private val disposable: CompositeDisposable? = null


    fun loadData(searchView: SearchView) {
        disposable?.add(
            Observable.create(ObservableOnSubscribe<String> { subscriber ->
                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextChange(newText: String?): Boolean {
                        subscriber.onNext(newText!!)
                        return false
                    }

                    override fun onQueryTextSubmit(query: String?): Boolean {
                        subscriber.onNext(query!!)
                        return false
                    }
                })
            })
                .map { text -> text.toLowerCase().trim() }
                .debounce(250, TimeUnit.MILLISECONDS)
                .distinct()
                .filter { text -> text.isNotBlank() }
                .subscribe { text -> loadCallback(text)}
        )
        disposable?.dispose()
        disposable?.clear()
    }


    private fun loadCallback(text: String) {
        val userList: MutableList<User> = userSearchInteractor.fetchUsers(text)
        userSearchView.showResult(userList)
    }
}