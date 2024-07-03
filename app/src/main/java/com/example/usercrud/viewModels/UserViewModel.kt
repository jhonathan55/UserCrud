package com.example.usercrud.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.usercrud.model.User
import com.example.usercrud.repository.UserRepository

class UserViewModel(application: Application): AndroidViewModel(application){

    private val repository = UserRepository(application)
    private val _allUsers = MutableLiveData<List<User>>()
    val allUsers get() = _allUsers

    init {
        loadUsers()
    }
    private fun loadUsers() {
        _allUsers.postValue(repository.getAllUsers())
    }

    fun insert(user: User) {
        repository.insert(user)
        loadUsers()
    }
}