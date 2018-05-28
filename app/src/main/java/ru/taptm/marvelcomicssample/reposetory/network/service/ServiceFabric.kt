package ru.taptm.marvelcomicssample.reposetory.network.service

object ServiceFabric {
    private val apiService: IApiService? = null

    fun getApiService(): IApiService {
        return apiService ?: RetrofitFabric.getBaseRetrofit().create(IApiService::class.java)
    }
}