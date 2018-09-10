package ru.taptm.marvelcomicssample.di.module

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.taptm.marvelcomicssample.reposetory.network.interceptors.ApiKeyInterceptor
import ru.taptm.marvelcomicssample.reposetory.network.interceptors.NetworkCheckInterceptor
import ru.taptm.marvelcomicssample.reposetory.network.service.IApiService
import ru.taptm.marvelcomicssample.utils.GsonHelper
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {
    companion object {
        private const val BASE_URL = "https://gateway.marvel.com/"
        private const val CONNECT_TIMEOUT_IN_SECONDS = 3 // тайм-аут в секундах для соединения
        private const val READ_TIMEOUT_IN_SECONDS = 100 // тайм-аут в секундах для чтения
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        // клиент добавлен для отладки
        return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(NetworkCheckInterceptor())
                .addInterceptor(ApiKeyInterceptor())
                .connectTimeout(CONNECT_TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)
                .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(GsonHelper.createGson()))
                .baseUrl(BASE_URL)
                .client(client)
                .build()
    }

    @Provides
    @Singleton
    fun providesPlacesApi(retrofit: Retrofit): IApiService {
        return retrofit.create<IApiService>(IApiService::class.java)
    }
}