package ru.taptm.marvelcomicssample.reposetory

import io.reactivex.Flowable
import io.reactivex.Single
import ru.taptm.marvelcomicssample.reposetory.local.FavouritesData
import ru.taptm.marvelcomicssample.reposetory.network.response.ComicsResponse

interface AppDataStorage {
    fun getComics(offset:Int): Single<ComicsResponse>
    fun getComicsDetails(comicsId: Int): Single<ComicsResponse>

    fun getFavourites(id: Long): Flowable<List<FavouritesData>>
    fun deleteFavourites(favouritesData: FavouritesData)
    fun insertFavourites(favouritesData: FavouritesData)
    fun getAllFavourites(): Single<List<FavouritesData>>
}