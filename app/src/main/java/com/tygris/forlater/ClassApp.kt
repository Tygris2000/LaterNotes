package com.tygris.forlater

import android.app.Application
import com.tygris.forlater.data.Reposit

class ClassApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Reposit.initialize(this)
    }
}