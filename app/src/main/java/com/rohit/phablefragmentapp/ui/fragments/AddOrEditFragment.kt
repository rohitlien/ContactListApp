package com.rohit.phablefragmentapp.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.rohit.phablefragmentapp.R
import com.rohit.phablefragmentapp.storage.UserData
import com.rohit.phablefragmentapp.ui.listeners.AddOrRemoveFragmentListener
import com.rohit.phablefragmentapp.ui.viewmodels.AddOrEditViewModel
import kotlinx.android.synthetic.main.fragment_add_or_edit.*

class AddOrEditFragment : Fragment() {

    private var user : UserData?=null

    private lateinit var viewModel: AddOrEditViewModel

    private var isEdit = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_or_edit, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(userData: UserData?) =
            AddOrEditFragment().apply {
                this.user = userData
            }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(AddOrEditViewModel::class.java)

        user?.let {
            isEdit = true
            userNameEt.setText(it.user_name)
            userEmailEt.setText(it.user_email)
            toolbarTitle.text = getString(R.string.text_edit_user)
        }

        submitBtn.setOnClickListener {
            validateInputs()
        }

        backBtn.setOnClickListener {
            destroyFragment()
        }

    }

    private fun validateInputs() {
        userNameEt.error = null
        userEmailEt.error = null
        val userName = userNameEt.text.toString().trim()
        val userEmail = userEmailEt.text.toString().trim()
        if(userName.isNotEmpty()){
            if(userEmail.isNotEmpty() && isEmailValid(userEmail)){
                hideKeyboard(requireContext(),requireView())
                if(isEdit){
                    user?.user_name = userName
                    user?.user_email = userEmail
                    viewModel.update(user!!)
                }else{
                    viewModel.addUser(userName,userEmail)
                }
                destroyFragment()
            }else{
                userEmailEt.error = getString(R.string.error_email)
            }
        }else{
            userNameEt.error = getString(R.string.hint_name)
        }
    }

    private fun destroyFragment() {
        val listener = activity as AddOrRemoveFragmentListener
        listener.popFragment()
    }

    private fun isEmailValid(email: String?): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }

    private fun hideKeyboard(context: Context, view: View) {
        try {
            val imm = (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            imm.hideSoftInputFromWindow(view.findFocus().windowToken, 0)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}