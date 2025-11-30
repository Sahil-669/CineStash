package org.example.cinestash

import android.app.Application
import org.example.cinestash.di.initKoin

class CineStashApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}