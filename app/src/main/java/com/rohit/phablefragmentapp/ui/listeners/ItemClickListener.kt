package com.rohit.phablefragmentapp.ui.listeners

import com.rohit.phablefragmentapp.storage.UserData

/**
 * Created by rohit on 8/22/20.
 */
interface ItemClickListener {
    fun onClick(user:UserData)
    fun onDelete(user: UserData)
    fun onUpdate(user: UserData)
}