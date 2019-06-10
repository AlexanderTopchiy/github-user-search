package com.wyverx.githubusersearch.view.usersearch.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import com.wyverx.githubusersearch.R
import com.wyverx.githubusersearch.data.usersearch.repository.UserSearchRepositoryImpl
import com.wyverx.githubusersearch.domain.usersearch.interactor.UserSearchInteractor
import com.wyverx.githubusersearch.presentation.usersearch.UserSearchPresenter
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import java.util.concurrent.TimeUnit

/**
 * This is main activity for search user.
 */
class UserSearchActivity : AppCompatActivity() {

    private lateinit var userSearchPresenter: UserSearchPresenter
    private lateinit var userSearchFragment: UserSearchFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_search)

        if (savedInstanceState == null) {
            createUserSearchPresenter()
            initUserSearchFragment()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater?.inflate(R.menu.search_menu, menu)

        val  searchView = menu?.findItem(R.id.search_view)?.actionView as SearchView

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
            .subscribe { text -> userSearchPresenter.loadCallback(text)}

        return true
    }


    private fun initUserSearchFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.user_search_main_container, userSearchFragment)
            .commit()
    }


    private fun createUserSearchPresenter() {
        userSearchFragment = UserSearchFragment.newInstance()
        userSearchPresenter = UserSearchPresenter.newInstance(
            UserSearchInteractor.newInstance(UserSearchRepositoryImpl.newInstance()),
            userSearchFragment)
    }
}
