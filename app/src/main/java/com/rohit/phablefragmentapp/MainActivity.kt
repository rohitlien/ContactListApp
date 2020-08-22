package com.rohit.phablefragmentapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rohit.phablefragmentapp.storage.UserData
import com.rohit.phablefragmentapp.ui.fragments.AddOrEditFragment
import com.rohit.phablefragmentapp.ui.fragments.ListFragment
import com.rohit.phablefragmentapp.ui.listeners.AddOrRemoveFragmentListener

class MainActivity : AppCompatActivity(),AddOrRemoveFragmentListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // add list fragment
        supportFragmentManager.beginTransaction().add(R.id.container,ListFragment.newInstance()).commit()

    }

    override fun addFragment(user: UserData?) {
        supportFragmentManager.beginTransaction().add(R.id.container,AddOrEditFragment.newInstance(user)).addToBackStack("").commit()
    }

    override fun popFragment() {
        supportFragmentManager.popBackStack()
    }


    override fun onBackPressed() {

        if(supportFragmentManager.fragments.size>1){
            supportFragmentManager.popBackStack()
        }else{
            super.onBackPressed()
        }
    }
}