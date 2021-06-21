package com.tygris.forlater.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.tygris.forlater.model.SomethingForLater

class Reposit private constructor(context:Context){

    private val dbbuilder : DataBase = Room.databaseBuilder(
        context.applicationContext,
        DataBase::class.java,
        "someDB"
    ).build()
    private val somedao = dbbuilder.dao()

     fun getAll() : LiveData<List<SomethingForLater>> = somedao.getAll()
     suspend fun insert(SomethingForLater: SomethingForLater) = somedao.insert(SomethingForLater)
     suspend fun delete(SomethingForLater: SomethingForLater) = somedao.delete(SomethingForLater)
     fun search(title : String) : LiveData<List<SomethingForLater>> = somedao.search()
    companion object{
        private var INSTANCE : Reposit? = null
        fun initialize(context: Context){
            if(INSTANCE==null){
                INSTANCE = Reposit(context)
                Log.d("omarisstupid", "Properly Initialized Repo................!")
            }
        }

        fun getInstance() : Reposit{
            return INSTANCE ?: throw IllegalStateException("repo NOOOT properly initialized")
        }
    }
}