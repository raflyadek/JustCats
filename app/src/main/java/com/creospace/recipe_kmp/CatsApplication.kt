package com.creospace.recipe_kmp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CatsApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CatsApplication)
            androidLogger()
            modules(appModules)
        }
    }
}