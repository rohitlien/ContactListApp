package com.rohit.phablefragmentapp.ui.listeners

import com.rohit.phablefragmentapp.storage.UserData

/**
 * Created by rohit on 8/22/20.
 */

interface AddOrRemoveFragmentListener {
    fun addFragment(user:UserData?)
    fun popFragment()
}