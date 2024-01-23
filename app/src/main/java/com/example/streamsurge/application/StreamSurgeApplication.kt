package com.example.streamsurge.application

import android.app.Application
import com.example.streamsurge.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class StreamSurgeApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        configKoin()
    }

    private fun configKoin() {
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@StreamSurgeApplication)
            modules(AppModule.appModules())
        }
    }
}