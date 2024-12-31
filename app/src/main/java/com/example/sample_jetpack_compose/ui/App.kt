package com.example.sample_jetpack_compose.ui

import android.app.Application
import com.example.sample_jetpack_compose.data.AppContainer
import com.example.sample_jetpack_compose.data.AppContainerImpl

class App : Application() {
    companion object {
        const val CATS_APP_URI = "https://developer.android.com/jetnews"
    }

    // AppContainer instance used by the rest of classes to obtain dependencies
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl(this)
    }
}