package com.sabinhantu

import android.app.Application

class SABApplication: Application() {
    companion object {
        lateinit var instance: SABApplication
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
    }
}