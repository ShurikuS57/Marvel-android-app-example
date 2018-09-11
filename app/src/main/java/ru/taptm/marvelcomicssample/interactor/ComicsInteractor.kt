package ru.taptm.marvelcomicssample.interactor

import ru.taptm.marvelcomicssample.comics.comicList.adapter.ComicsCell
import ru.taptm.marvelcomicssample.comics.comicsdetail.model.ComicsDetailsModel
import ru.taptm.marvelcomicssample.reposetory.IAppDataStorage
import ru.taptm.marvelcomicssample.reposetory.network.response.ComicsResponse

class ComicsInteractor(private val repository: IAppDataStorage) {
    private var currentOffsetPage = 0
    private var totalPage = 0
    private val cells = ArrayList<ComicsCell>()

    fun loadComics() = repository
            .getComics(currentOffsetPage)
            .map {
                this.currentOffsetPage += it.dataResponse.count ?: 0
                totalPage = it.dataResponse.total ?: 0
                return@map prepareComicsList(it)
            }

    private fun prepareComicsList(result: ComicsResponse): ArrayList<ComicsCell> {
        result.dataResponse.results?.let {
            val resultData = result.dataResponse.results
            resultData?.forEach {
                val cell = ComicsCell(it)
                cells.add(cell)
            }
        }
        return cells
    }

    fun getAllFavourites() = repository.getAllFavourites()

    fun isShouldLoadMore(): Boolean {
        return currentOffsetPage < totalPage
    }

    fun clearComicsData() {
        currentOffsetPage = 0
        cells.clear()
    }

    fun loadComicsDetails(comicsId: Int) = repository
            .getComicsDetails(comicsId)
            .map { result ->
                var comicsModel: ComicsDetailsModel? = null
                result.dataResponse.results?.let {
                    val resultData = result.dataResponse.results
                    comicsModel = ComicsDetailsModel(resultData)
                }
                return@map comicsModel
            }
}