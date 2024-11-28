package com.android.myou

import android.app.Application
import com.android.myou.util.SharedPref

class App : Application() {
    companion object {
        lateinit var prefs: SharedPref
    }

    override fun onCreate() {
        prefs = SharedPref(applicationContext)
        super.onCreate()
    }
}