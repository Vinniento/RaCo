package com.example.raco

import android.app.Application
import timber.log.Timber
import timber.log.Timber.DebugTree

class RacoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}