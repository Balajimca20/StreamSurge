package com.example.streamsurge.application

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import coil.util.DebugLogger
import com.example.streamsurge.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class StreamSurgeApplication:Application(),ImageLoaderFactory {

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

    override fun newImageLoader(): ImageLoader {
        return ImageLoader(this).newBuilder()
            .memoryCachePolicy(CachePolicy.ENABLED)
            .memoryCache { MemoryCache.Builder(this)
                .maxSizeBytes(1)
                .strongReferencesEnabled(true)
                .build()
            }
            .diskCachePolicy(CachePolicy.ENABLED)
            .diskCache {
                DiskCache.Builder()
                    .maxSizeBytes(3)
                    .directory(cacheDir)
                    .build()
            }
            .logger(DebugLogger())
            .build()
    }
}