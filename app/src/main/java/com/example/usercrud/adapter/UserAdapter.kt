package com.example.usercrud.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.usercrud.R
import com.example.usercrud.model.User

class UserAdapter: RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private var userList = emptyList<User>()

    class UserViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val username: TextView = itemView.findViewById(R.id.tvUsername)
        val email: TextView = itemView.findViewById(R.id.tvEmail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser = userList[position]
        holder.username.text = currentUser.username
        holder.email.text = currentUser.email
    }
    override fun getItemCount(): Int {
        return userList.size
    }

    fun setUsers(users: List<User>){
        userList = users
        notifyDataSetChanged()
    }

}