package com.creospace.recipe_kmp

import android.app.Application
import org.koin.core.context.startKoin

class CatsApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(appModules)
        }
    }
}