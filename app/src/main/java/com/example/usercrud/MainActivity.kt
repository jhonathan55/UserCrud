package com.example.usercrud

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.usercrud.adapter.UserAdapter
import com.example.usercrud.databinding.MainActivityBinding
import com.example.usercrud.model.User
import com.example.usercrud.viewModels.UserViewModel

class MainActivity : AppCompatActivity() {
    private val userViewModel: UserViewModel by viewModels()
    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager= LinearLayoutManager(this)
        binding.recyclerView.adapter = UserAdapter()
        //todo observer listUsers
        userViewModel.allUsers.observe(this, { users ->
            users?.let { (binding.recyclerView.adapter as UserAdapter).setUsers(it) }
        })

        binding.btnAddUser.setOnClickListener() {
            val username = binding.etUsername.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                val user = User(username = username, email = email, password = password)
                userViewModel.insert(user)
            }
        }

    }
}

