package ru.taptm.marvelcomicssample.interactor

import ru.taptm.marvelcomicssample.reposetory.IAppDataStorage
import ru.taptm.marvelcomicssample.reposetory.local.FavouritesData

class FavouritesInteractor(private val repository: IAppDataStorage) {

    fun loadFavourites(comicsId: Long) = repository.getFavourites(comicsId)

    fun insertFavourites(favouritesData: FavouritesData) {
        repository.insertFavourites(favouritesData)
    }

    fun deleteFavourites(favouritesData: FavouritesData) {
        repository.deleteFavourites(favouritesData)
    }
}