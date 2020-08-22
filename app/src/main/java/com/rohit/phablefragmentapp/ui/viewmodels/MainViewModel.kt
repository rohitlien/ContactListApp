package com.rohit.phablefragmentapp.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.rohit.phablefragmentapp.storage.UserData
import com.rohit.phablefragmentapp.ui.repository.UserRepository

/**
 * Created by rohit on 8/22/20.
 */

class MainViewModel (application: Application) : AndroidViewModel(application) {

    private val userRepository: UserRepository
    internal val allUsers: LiveData<List<UserData>>
    init {
        userRepository = UserRepository(application)
        allUsers = userRepository.getAllUsers()
    }

    fun delete(userData: UserData) {
        userRepository.delete(userData)
    }

}