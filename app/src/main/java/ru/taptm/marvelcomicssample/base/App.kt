package ru.taptm.marvelcomicssample.base

import android.app.Application
import android.content.Context
import com.squareup.leakcanary.LeakCanary
import ru.taptm.marvelcomicssample.BuildConfig

class App : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: App? = null

        fun context() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        initLeakDetector()
    }

    private fun initLeakDetector() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this)
        }
    }
}