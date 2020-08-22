package com.rohit.phablefragmentapp.ui.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.rohit.phablefragmentapp.storage.UserDao
import com.rohit.phablefragmentapp.storage.UserData
import com.rohit.phablefragmentapp.storage.UserDatabase

/**
 * Created by rohit on 8/22/20.
 */

class UserRepository (application: Application) {

    private val userDao: UserDao
    private val listLiveData: LiveData<List<UserData>>

    init {
        val userDatabase = UserDatabase.getDatabase(application)
        userDao = userDatabase?.userDao()!!
        listLiveData = userDao.getUsers()
    }

    fun getAllUsers(): LiveData<List<UserData>> {
        return listLiveData
    }

    fun insert(userData: UserData) {
        insertAsyncTask(userDao).execute(userData)
    }

    fun update(userData: UserData) {
        updateAsyncTask(userDao).execute(userData)
    }

    fun delete(userData: UserData) {
        deleteAsyncTask(userDao).execute(userData)
    }

    private class insertAsyncTask internal constructor(private val mAsyncTaskDao: UserDao) : AsyncTask<UserData, Void, Void>() {

        override fun doInBackground(vararg params: UserData): Void? {
            mAsyncTaskDao.setNewUser(params[0])
            return null
        }
    }

    private class updateAsyncTask internal constructor(private val mAsyncTaskDao: UserDao) : AsyncTask<UserData, Void, Void>() {

        override fun doInBackground(vararg params: UserData): Void? {
            mAsyncTaskDao.updateUser(params[0])
            return null
        }
    }

    private class deleteAsyncTask internal constructor(private val mAsyncTaskDao: UserDao) : AsyncTask<UserData, Void, Void>() {

        override fun doInBackground(vararg params: UserData): Void? {
            mAsyncTaskDao.deleteUser(params[0])
            return null
        }
    }

}