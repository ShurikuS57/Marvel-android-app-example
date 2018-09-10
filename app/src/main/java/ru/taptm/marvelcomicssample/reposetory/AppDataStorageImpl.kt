package ru.taptm.marvelcomicssample.reposetory

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.taptm.marvelcomicssample.reposetory.local.FavouritesData
import ru.taptm.marvelcomicssample.reposetory.local.IFavouritesDataDao
import ru.taptm.marvelcomicssample.reposetory.network.ParameterCreater
import ru.taptm.marvelcomicssample.reposetory.network.response.ComicsResponse
import ru.taptm.marvelcomicssample.reposetory.network.service.IApiService
import ru.taptm.marvelcomicssample.utils.RxSchedulersProvider

open class AppDataStorageImpl(private val apiService: IApiService, private val favouritesDao: IFavouritesDataDao) : IAppDataStorage {

    override fun getComics(offset: Int): Single<ComicsResponse> {
        return apiService.getComics(ParameterCreater.createComicsParams(offset))
                .compose(RxSchedulersProvider.getComputationToMainTransformerSingle())
    }

    override fun getComicsDetails(comicsId: Int): Single<ComicsResponse> {
        return apiService.getComicsDetails(comicsId)
                .compose(RxSchedulersProvider.getComputationToMainTransformerSingle())
    }

    override fun getFavourites(id: Long): Flowable<List<FavouritesData>> {
        return favouritesDao.getFavourites(id)
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun deleteFavourites(favouritesData: FavouritesData) {
        Completable.fromCallable {
            favouritesDao.delete(favouritesData)
        }.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe()
    }

    override fun insertFavourites(favouritesData: FavouritesData) {
        Completable.fromCallable {
            favouritesDao.insert(favouritesData)
        }.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe()
    }

    override fun getAllFavourites(): Single<List<FavouritesData>> {
        return favouritesDao.getAllFavourites()
                .compose(RxSchedulersProvider.getComputationToMainTransformerSingle())
    }

}