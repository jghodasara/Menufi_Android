package com.android.menufi

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseClass(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE user(ID INTEGER PRIMARY KEY AUTOINCREMENT, firstName TEXT, lastName TEXT, email TEXT, password TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS user")
    }

    fun Insert(firstName: String?,lastName: String?,email: String?, password: String?): Boolean {
        val sqLiteDatabase = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("firstName", firstName)
        contentValues.put("lastName", lastName)
        contentValues.put("email", email)
        contentValues.put("password", password)
        val result = sqLiteDatabase.insert("user", null, contentValues)
        return if (result == -1L) {
            false
        } else {
            true
        }
    }

    fun CheckEmail(email: String): Boolean {
        val sqLiteDatabase = this.writableDatabase
        val cursor =
            sqLiteDatabase.rawQuery("SELECT * FROM user WHERE email=?", arrayOf(email))
        return if (cursor.count > 0) {
            false
        } else {
            true
        }
    }

    fun CheckLogin(email: String, password: String): Boolean {
        val sqLiteDatabase = this.readableDatabase
        val cursor = sqLiteDatabase.rawQuery(
            "SELECT * FROM user WHERE email=? AND password=?",
            arrayOf(email, password)
        )
        return if (cursor.count > 0) {
            true
        } else {
            false
        }
    }

    fun getDetails(email:String):Cursor {
        val sqLiteDatabase = this.readableDatabase
        val cursor = sqLiteDatabase.rawQuery(
            "SELECT * FROM user WHERE email=?",
            arrayOf(email)
        )
        cursor.moveToFirst()
        return cursor

    }

    fun deleteAccount(email:String):Boolean {
        val sqLiteDatabase = this.writableDatabase
        val cursor =
            sqLiteDatabase.rawQuery("DELETE FROM user WHERE email=?", arrayOf(email))
        return if (cursor.count > 0) {
            false
        } else {
            true
        }
    }

    fun updateDetails(fName:String,lName:String):Boolean {
        val sqLiteDatabase = this.writableDatabase
        val cursor =
            sqLiteDatabase.rawQuery("UPDATE user SET firstname=?,lastname=?", arrayOf(fName,lName))
        return if (cursor.count > 0) {
            false
        } else {
            true
        }
    }

    companion object {
        const val DATABASE_NAME = "menufi.db"
    }
}