package com.nectarofthepeople.spectoroster

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory

class SpectoRosterApplication : Application(), ImageLoaderFactory {
    lateinit var container: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .respectCacheHeaders(false)
            .allowHardware(false)
            .build()
    }
}
