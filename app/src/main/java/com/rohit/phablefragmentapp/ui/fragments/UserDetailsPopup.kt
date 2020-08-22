package com.rohit.phablefragmentapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.rohit.phablefragmentapp.R
import com.rohit.phablefragmentapp.storage.UserData
import com.rohit.phablefragmentapp.ui.listeners.ItemClickListener
import kotlinx.android.synthetic.main.dialog_user_details.*

/**
 * Created by rohit on 8/22/20.
 */

class UserDetailsPopup : BottomSheetDialogFragment(){

    private lateinit var userData:UserData
    private var itemClickListener:ItemClickListener?=null

    companion object {
        fun newInstance(userData:UserData,itemClickListener:ItemClickListener): UserDetailsPopup {
            val dialogFragment = UserDetailsPopup()
            dialogFragment.userData = userData
            dialogFragment.itemClickListener = itemClickListener

            return dialogFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(
            R.layout.dialog_user_details, container,
            false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        userName.text = userData.user_name
        userEmail.text = userData.user_email

        deleteBtn.setOnClickListener {
            itemClickListener?.onDelete(userData)
            dismiss()
        }
        editBtn.setOnClickListener {
            itemClickListener?.onUpdate(userData)
            dismiss()
        }

    }
}