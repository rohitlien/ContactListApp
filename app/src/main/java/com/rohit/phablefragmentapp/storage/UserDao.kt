package com.rohit.phablefragmentapp.storage

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setNewUser(user: UserData)

    @Query("SELECT * from user_table ORDER BY id ASC")
    fun getUsers() : LiveData<List<UserData>>

    @Delete
    fun deleteUser(user: UserData)

    @Update
    fun updateUser(user: UserData)

}