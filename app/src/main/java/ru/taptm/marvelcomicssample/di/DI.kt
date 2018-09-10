package ru.taptm.marvelcomicssample.di

import android.content.Context

object DI {

    private lateinit var componentManager: ComponentManager

    fun init(context: Context) {
        componentManager = ComponentManager(context)
    }

    fun componentManager(): ComponentManager = componentManager
}