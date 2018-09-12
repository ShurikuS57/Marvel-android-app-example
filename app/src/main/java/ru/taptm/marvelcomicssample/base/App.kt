package ru.taptm.marvelcomicssample.base

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import ru.taptm.marvelcomicssample.BuildConfig
import ru.taptm.marvelcomicssample.di.DI

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        DI.init(this)
        initLeakDetector()
    }

    private fun initLeakDetector() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this)
        }
    }
}