package org.example.cinestash

import android.app.Application
import org.example.cinestash.di.initKoin
import org.koin.android.ext.koin.androidContext

class CineStashApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@CineStashApp)
        }
    }
}