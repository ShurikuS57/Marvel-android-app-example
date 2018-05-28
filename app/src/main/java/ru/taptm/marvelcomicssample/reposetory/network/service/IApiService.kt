package ru.taptm.marvelcomicssample.reposetory.network.service

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap
import ru.taptm.marvelcomicssample.reposetory.network.response.ComicsResponse

interface IApiService {
    @GET("v1/public/comics")
    fun getComics(@QueryMap params: HashMap<String, String>): Single<ComicsResponse>

    @GET("v1/public/comics/{comics_id}")
    fun getComicsDetails(@Path("comics_id") comicsId: Int): Single<ComicsResponse>
}