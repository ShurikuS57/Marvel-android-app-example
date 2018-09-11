package ru.taptm.marvelcomicssample.interactor

import android.net.Uri
import ru.taptm.marvelcomicssample.comics.comicList.adapter.ComicsCell
import ru.taptm.marvelcomicssample.reposetory.IAppDataStorage
import ru.taptm.marvelcomicssample.reposetory.local.FavouritesData
import ru.taptm.marvelcomicssample.reposetory.network.response.ImageResponse
import ru.taptm.marvelcomicssample.reposetory.network.response.ResultResponse

class FavouritesInteractor(private val repository: IAppDataStorage) {

    fun loadFavourites(comicsId: Long) = repository.getFavourites(comicsId)

    fun insertFavourites(favouritesData: FavouritesData) {
        repository.insertFavourites(favouritesData)
    }

    fun deleteFavourites(favouritesData: FavouritesData) {
        repository.deleteFavourites(favouritesData)
    }

    fun loadAllFavourites() = repository
            .getAllFavourites()
            .map { data ->
                val cells: ArrayList<ComicsCell> = ArrayList()
                if (data.isNotEmpty()) {
                    data.forEach {
                        val resultResponse = ResultResponse()
                        resultResponse.id = it.comicsId.toInt()
                        resultResponse.title = it.title
                        val uri = Uri.parse(it.imageUrl)
                        resultResponse.images = arrayListOf(
                                ImageResponse(uri.toString().replace("/portrait_xlarge.jpg", ""), "jpg")
                        )
                        val cell = ComicsCell(resultResponse)
                        cells.add(cell)
                    }
                }
                return@map cells
            }
}