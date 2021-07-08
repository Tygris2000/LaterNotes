package com.tygris.forlater.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.tygris.forlater.data.Reposit
import com.tygris.forlater.data.UserPrefs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LaterViewModel(application: Application) : AndroidViewModel(application) {
    val repoi : Reposit = Reposit.getInstance()

    init {
         UserPrefs.init(application.applicationContext)
    }


    fun getAll() : LiveData<List<SomethingForLater>> = repoi.getAll()
    fun searchEnteries(title: String) : LiveData<List<SomethingForLater>> = repoi.search(title)
    fun insertNew(something : SomethingForLater){
        viewModelScope.launch(Dispatchers.IO){
            repoi.insert(something)
        }
    }
    fun delete(something : SomethingForLater){
        viewModelScope.launch(Dispatchers.IO){
            repoi.delete(something)
        }
    }



    fun mTheme() : Int{
        return when (UserPrefs.isContain("isdark")){
            UserPrefs.isContain("isdark") && UserPrefs.getboolean("isdark") -> 1
            UserPrefs.isContain("isdark") && !UserPrefs.getboolean("isdark") -> 0
            else -> -1
        }
    }

}