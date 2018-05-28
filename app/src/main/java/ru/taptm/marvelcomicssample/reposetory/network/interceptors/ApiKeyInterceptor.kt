package ru.taptm.marvelcomicssample.reposetory.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import ru.taptm.marvelcomicssample.BuildConfig
import ru.taptm.marvelcomicssample.reposetory.network.Params

internal class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url()
        val url = originalUrl.newBuilder()
                .addQueryParameter(Params.TS, 1.toString())
                .addQueryParameter(Params.API_KEY, BuildConfig.MARVEL_API_KEY)
                .addQueryParameter(Params.API_HASH, BuildConfig.MARVEL_HASH_KEY)
                .build()

        val requestBuilder = originalRequest.newBuilder().url(url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }

}