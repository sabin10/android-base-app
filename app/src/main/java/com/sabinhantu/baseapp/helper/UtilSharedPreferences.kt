package com.sabinhantu.baseapp.helper

import android.content.Context

object UtilSharedPreferences {

    val USER_ID = "userId"

    fun saveUser(context: Context, id: String) {
        val editor = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE).edit()
        editor.putString(USER_ID, id)
        editor.apply()
    }

    fun getUserId(context: Context): String {
        val sharedPreferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        return sharedPreferences.getString(USER_ID, "") ?: ""
    }

}