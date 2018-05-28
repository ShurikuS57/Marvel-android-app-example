package ru.taptm.marvelcomicssample.reposetory.network.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.taptm.marvelcomicssample.BuildConfig
import ru.taptm.marvelcomicssample.reposetory.network.interceptors.ApiKeyInterceptor
import ru.taptm.marvelcomicssample.reposetory.network.interceptors.NetworkCheckInterceptor
import ru.taptm.marvelcomicssample.utils.GsonHelper
import java.util.concurrent.TimeUnit

object RetrofitFabric {
    private val BASE_URL = "https://gateway.marvel.com/"
    private val CONNECT_TIMEOUT_IN_SECONDS = 3 // тайм-аут в секундах для соединения
    private val READ_TIMEOUT_IN_SECONDS = 100 // тайм-аут в секундах для чтения
    private var baseRetrofit: Retrofit? = null

    fun getBaseRetrofit(): Retrofit {
        if (baseRetrofit == null) {
            baseRetrofit = getRetrofit(BASE_URL)
        }
        return baseRetrofit as Retrofit
    }

    fun getRetrofit(baseUrl: String): Retrofit {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = if (BuildConfig.BUILD_TYPE.equals("debug")) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        // клиент добавлен для отладки
        val client = OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(NetworkCheckInterceptor())
                .addInterceptor(ApiKeyInterceptor())
                .connectTimeout(CONNECT_TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)
                .build()

        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(GsonHelper.createGson()))
                .baseUrl(baseUrl)
                .client(client)
                .build()
    }
}