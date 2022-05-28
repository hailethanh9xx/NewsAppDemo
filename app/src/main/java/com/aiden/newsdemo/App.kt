package com.aiden.newsdemo

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
