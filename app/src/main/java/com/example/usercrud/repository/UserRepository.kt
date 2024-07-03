package com.example.usercrud.repository

import android.content.ContentValues
import android.content.Context
import com.example.usercrud.db.UserDatabaseHelper
import com.example.usercrud.model.User

class UserRepository(context: Context) {

    private val dbHelper = UserDatabaseHelper(context)

    fun insert(user:User):Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(UserDatabaseHelper.COLUMN_USERNAME, user.username)
            put(UserDatabaseHelper.COLUMN_EMAIL, user.email)
            put(UserDatabaseHelper.COLUMN_PASSWORD, user.password)
        }
        return db.insert(UserDatabaseHelper.TABLE_USER, null, values)
    }

    fun getAllUsers():List<User> {
        val db = dbHelper.readableDatabase
        val cursor = db.query(UserDatabaseHelper.TABLE_USER, null, null, null, null, null, null)
        val users = mutableListOf<User>()
        with(cursor) {
            while (moveToNext()) {
                val username = if (!isNull(getColumnIndexOrThrow(UserDatabaseHelper.COLUMN_USERNAME))) {
                    getString(getColumnIndexOrThrow(UserDatabaseHelper.COLUMN_USERNAME))
                } else {
                    ""
                }
                val email = if (!isNull(getColumnIndexOrThrow(UserDatabaseHelper.COLUMN_EMAIL))) {
                    getString(getColumnIndexOrThrow(UserDatabaseHelper.COLUMN_EMAIL))
                } else {
                    ""
                }
                val password = if (!isNull(getColumnIndexOrThrow(UserDatabaseHelper.COLUMN_PASSWORD))) {
                    getString(getColumnIndexOrThrow(UserDatabaseHelper.COLUMN_PASSWORD))
                } else {
                    ""
                }
                val user = User(id=0, username, email, password)
                users.add(user)
            }
        }
        cursor.close()
        return users
    }

}