package com.wyverx.githubusersearch.view.usersearch.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.SearchView
import com.wyverx.githubusersearch.R
import com.wyverx.githubusersearch.data.usersearch.repository.UserSearchRepositoryImpl
import com.wyverx.githubusersearch.domain.usersearch.interactor.UserSearchInteractor
import com.wyverx.githubusersearch.domain.usersearch.model.User
import com.wyverx.githubusersearch.presentation.usersearch.UserSearchPresenter
import com.wyverx.githubusersearch.presentation.usersearch.UserSearchView

/**
 * This class represents view layer.
 */
class UserSearchFragment : Fragment(), UserSearchView {

    private lateinit var userSearchPresenter: UserSearchPresenter
    private lateinit var userSearchRecyclerViewAdapter: UserSearchRecyclerViewAdapter
    private lateinit var userSearchRecyclerView: RecyclerView
    private lateinit var searchView: SearchView


    companion object {
        fun newInstance(): UserSearchFragment {
            return UserSearchFragment()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userSearchPresenter = UserSearchPresenter.newInstance(
            UserSearchInteractor.newInstance(UserSearchRepositoryImpl.newInstance()),
            this)
        userSearchRecyclerViewAdapter = UserSearchRecyclerViewAdapter()
    }


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_user_search_list, container, false)

        userSearchRecyclerView = view as RecyclerView
        userSearchRecyclerView.layoutManager = LinearLayoutManager(view.context)
        userSearchRecyclerView.adapter = userSearchRecyclerViewAdapter

        userSearchPresenter.loadData(searchView)
        return userSearchRecyclerView
    }


    override fun onCreateOptionsMenu(menu: Menu?, menuInflater: MenuInflater?) {
        menuInflater?.inflate(R.menu.search_menu, menu)
        searchView = menu?.findItem(R.id.search_view)?.actionView as SearchView
    }


    override fun showResult(userList: MutableList<User>) {
        userSearchRecyclerViewAdapter.updateDataSet(userList)
    }

}
