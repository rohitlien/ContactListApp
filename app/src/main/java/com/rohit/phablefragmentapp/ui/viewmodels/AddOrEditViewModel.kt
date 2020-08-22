package com.rohit.phablefragmentapp.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.rohit.phablefragmentapp.storage.UserData
import com.rohit.phablefragmentapp.ui.repository.UserRepository

/**
 * Created by rohit on 8/22/20.
 */

class AddOrEditViewModel (application: Application) : AndroidViewModel(application) {

    private val userRepository: UserRepository
    internal val allUsers: LiveData<List<UserData>>

    init {
        userRepository = UserRepository(application)
        allUsers = userRepository.getAllUsers()
    }

    fun insert(userData: UserData) {
        userRepository.insert(userData)
    }

    fun update(userData: UserData) {
        userRepository.update(userData)
    }

    fun addUser(userName: String, userEmail: String) {
        val userData = UserData(0,user_name = userName,user_email = userEmail)
        insert(userData)
    }
}