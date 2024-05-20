package org.example.project

import android.app.Application
import di.KoinInitializer

class KMPApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        KoinInitializer(applicationContext).init()
    }
}