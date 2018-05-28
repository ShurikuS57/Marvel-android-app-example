package ru.taptm.marvelcomicssample.utils

import android.content.Context
import android.net.ConnectivityManager
import ru.taptm.marvelcomicssample.base.App

open class NetworkUtils {
    companion object {
        fun isOnline(): Boolean {
            val cm = App.context().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = cm.activeNetworkInfo
            return netInfo != null && netInfo.isConnectedOrConnecting
        }
    }
}