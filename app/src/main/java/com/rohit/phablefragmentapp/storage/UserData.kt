package com.rohit.phablefragmentapp.storage

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserData(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int,

    @ColumnInfo(name = "user_name")
    var user_name: String,

    @ColumnInfo(name = "user_email")
    var user_email: String

)