package ru.taptm.marvelcomicssample.reposetory.network.interceptors

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response
import ru.taptm.marvelcomicssample.reposetory.network.exceptions.NoNetworkException
import ru.taptm.marvelcomicssample.utils.NetworkUtils
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class NetworkCheckInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        if (!NetworkUtils.isOnline(context)) {
            throw NoNetworkException("No network connection")
        }
        try {
            return chain.proceed(requestBuilder.build())
        } catch (e: SocketTimeoutException) {
            throw NoNetworkException()
        } catch (e: UnknownHostException) {
            throw NoNetworkException()
        }
    }
}