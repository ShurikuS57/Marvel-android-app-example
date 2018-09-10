package ru.taptm.marvelcomicssample.di

import android.content.Context
import ru.taptm.marvelcomicssample.di.component.AppComponent
import ru.taptm.marvelcomicssample.di.component.DaggerAppComponent
import ru.taptm.marvelcomicssample.di.module.AppModule

class ComponentManager(val context: Context) {
    val internalAppComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
                .appModule(AppModule(context)).build()
    }

    fun appComponent() = internalAppComponent
}