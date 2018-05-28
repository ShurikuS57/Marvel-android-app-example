package ru.taptm.marvelcomicssample.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder

object GsonHelper {
    fun createGson(): Gson {
        return GsonBuilder().create()
    }
}
