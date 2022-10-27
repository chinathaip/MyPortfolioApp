package com.example.myportfolioapp

import android.app.Application
import com.example.myportfolioapp.di.module
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(module)
        }
    }
}