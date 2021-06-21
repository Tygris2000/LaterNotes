package com.tygris.forlater.model

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkRequest
import com.tygris.forlater.data.Reposit
import com.tygris.forlater.data.UserPrefs
import com.tygris.forlater.worker.NotifyWorker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import java.util.concurrent.TimeUnit

class LaterViewModel(application: Application) : AndroidViewModel(application) {
    val repoi : Reposit = Reposit.getInstance()

    init {
        val prefref = UserPrefs.init(application.applicationContext)
    }


    fun getAll() : LiveData<List<SomethingForLater>> = repoi.getAll()
    fun workerRegister(date : Date){
      val workreq :WorkRequest = OneTimeWorkRequestBuilder<NotifyWorker>()
          .setInitialDelay(2,TimeUnit.MINUTES).build()

    }
    fun insertNew(something : SomethingForLater){
        viewModelScope.launch(Dispatchers.IO){
            repoi.insert(something)
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