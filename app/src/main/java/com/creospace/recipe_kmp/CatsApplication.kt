package com.creospace.recipe_kmp

import android.app.Application

class CatsApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}