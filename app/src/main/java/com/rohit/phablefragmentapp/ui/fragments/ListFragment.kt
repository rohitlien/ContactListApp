package com.rohit.phablefragmentapp.ui.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.rohit.phablefragmentapp.R
import com.rohit.phablefragmentapp.storage.UserData
import com.rohit.phablefragmentapp.ui.adapters.UserListAdapter
import com.rohit.phablefragmentapp.ui.listeners.AddOrRemoveFragmentListener
import com.rohit.phablefragmentapp.ui.listeners.ItemClickListener
import com.rohit.phablefragmentapp.ui.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.list_fragment.*

class ListFragment : Fragment(),ItemClickListener {

    companion object {
        fun newInstance() = ListFragment()
    }

    private lateinit var viewModel: MainViewModel

    private var userListAdapter : UserListAdapter?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        usersRv.layoutManager = LinearLayoutManager(requireContext())

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.allUsers.observe(requireActivity(), Observer {
            setData(it)
        })

        addUserBtn.setOnClickListener {
            openAddFragment()
        }
    }

    private fun openAddFragment() {
        val listener = activity as AddOrRemoveFragmentListener
        listener.addFragment(null)
    }

    private fun setData(userDatas: List<UserData>?) {
        if(userDatas!=null && userDatas.isNotEmpty()){
            usersRv.visibility = View.VISIBLE
            noUsersTv.visibility = View.GONE
            if(userListAdapter==null){
                userListAdapter = UserListAdapter(userDatas,this)
                usersRv.adapter = userListAdapter
            }else{
                userListAdapter?.updateData(userDatas)
            }
        }else{
            usersRv.visibility = View.GONE
            noUsersTv.visibility = View.VISIBLE
        }
    }

    override fun onClick(user: UserData) {
        activity?.supportFragmentManager?.let {
            val dialogUserDetails = UserDetailsPopup.newInstance(user, this)
            dialogUserDetails.show(it,"detailsDialog")
        }

    }

    override fun onDelete(user: UserData) {
        viewModel.delete(user)
    }

    override fun onUpdate(user: UserData) {
        val listener = activity as AddOrRemoveFragmentListener
        listener.addFragment(user)
    }

}