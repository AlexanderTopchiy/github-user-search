package com.wyverx.githubusersearch.view.usersearch.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.wyverx.githubusersearch.R

class UserSearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_search)

        if (savedInstanceState == null) {
            initUserSearchFragment()
        }
    }


    private fun initUserSearchFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.user_search_main_container, UserSearchFragment.newInstance())
            .commit()
    }
}
