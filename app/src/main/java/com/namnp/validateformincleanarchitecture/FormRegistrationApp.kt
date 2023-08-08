package com.namnp.validateformincleanarchitecture

import android.app.Application
import com.namnp.validateformincleanarchitecture.di.appModule
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

//@HiltAndroidApp
class FormRegistrationApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@FormRegistrationApp)
            modules(appModule)
        }
    }
}