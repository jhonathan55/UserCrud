package com.example.usercrud.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserDatabaseHelper(context:Context)
    :SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
        companion object {
            const val DATABASE_NAME = "user.db"
            const val DATABASE_VERSION = 1

            const val TABLE_USER = "user"
            const val COLUMN_ID = "id"
            const val COLUMN_USERNAME = "username"
            const val COLUMN_EMAIL = "email"
            const val COLUMN_PASSWORD = "password"
        }

    override fun onCreate(db: SQLiteDatabase){
        val createTableUser = ("CREATE TABLE " + TABLE_USER + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_USERNAME + " TEXT,"
                + COLUMN_EMAIL + " TEXT,"
                + COLUMN_PASSWORD + " TEXT" + ")")
        db.execSQL(createTableUser)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int){
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USER")
        onCreate(db)
    }

}