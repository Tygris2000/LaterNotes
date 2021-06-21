package com.tygris.forlater.data

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData


object UserPrefs {
    private const val NAME = "user_prefs"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var prefs : SharedPreferences
    private val darkornot : Boolean = false
    private  val darkkey = Pair("isdark", false)

    fun init(context: Context){
        prefs = context.getSharedPreferences(NAME, MODE)

    }


    private inline fun SharedPreferences.edit(operation:
                                                  (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }




    fun setDark(value: Boolean){
        prefs.edit{
            it.putBoolean(darkkey.first,value)
        }
    }
    fun getboolean(key: String): Boolean{
        return prefs.getBoolean(key, darkkey.second)
    }
    fun isContain(key: String): Boolean = prefs.contains(key)


}