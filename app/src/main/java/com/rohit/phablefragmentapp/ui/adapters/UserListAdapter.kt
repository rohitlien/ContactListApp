package com.rohit.phablefragmentapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.rohit.phablefragmentapp.R
import com.rohit.phablefragmentapp.databinding.ItemUserBinding
import com.rohit.phablefragmentapp.storage.UserData
import com.rohit.phablefragmentapp.ui.listeners.ItemClickListener

/**
 * Created by rohit on 8/22/20.
 */
class UserListAdapter (private var userArrayList: List<UserData>?,
                       private val itemClickListener: ItemClickListener)
    : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemUserBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_user, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataModel = userArrayList!![position]
        holder.itemRowBinding.model = dataModel
        holder.itemRowBinding.itemClickListener = itemClickListener
    }


    override fun getItemCount(): Int {
        return userArrayList!!.size
    }

    fun updateData(userDatas: List<UserData>) {
        this.userArrayList = userDatas
        notifyDataSetChanged()
    }


    inner class ViewHolder(var itemRowBinding: ItemUserBinding)
        : RecyclerView.ViewHolder(itemRowBinding.root)
}
