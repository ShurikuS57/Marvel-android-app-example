package ru.taptm.marvelcomicssample.comics.favourites.presenter

import android.net.Uri
import com.arellomobile.mvp.InjectViewState
import io.reactivex.disposables.CompositeDisposable
import ru.taptm.marvelcomicssample.base.BasePresenter
import ru.taptm.marvelcomicssample.comics.comicList.adapter.ComicsCell
import ru.taptm.marvelcomicssample.comics.favourites.view.IFavouritesView
import ru.taptm.marvelcomicssample.reposetory.AppDataStorageImpl
import ru.taptm.marvelcomicssample.reposetory.local.RoomDatabaseStorage
import ru.taptm.marvelcomicssample.reposetory.network.response.ImageResponse
import ru.taptm.marvelcomicssample.reposetory.network.response.ResultResponse
import ru.taptm.marvelcomicssample.reposetory.network.service.ServiceFabric

@InjectViewState
class FavouritesPresenter: BasePresenter<IFavouritesView>() {
    private val disposable: CompositeDisposable
        get() = CompositeDisposable()

    private val repository: AppDataStorageImpl
        get() = AppDataStorageImpl(ServiceFabric.getApiService(), RoomDatabaseStorage.getInstance().favouritesDataDao())

    fun loadData() {
        disposable.add(repository.getAllFavourites().subscribe({
            data ->
            if (data.isNotEmpty()) {
                val cells: ArrayList<ComicsCell> = ArrayList()
                data.forEach {
                    val resultResponse = ResultResponse()
                    resultResponse.id = it.comicsId.toInt()
                    resultResponse.title = it.title
                    val uri = Uri.parse(it.imageUrl)
                    resultResponse.images = arrayListOf(
                            ImageResponse(uri.toString().replace("/portrait_xlarge.jpg", ""), "jpg")
                    )
                    val cell = ComicsCell(resultResponse)
                    cell.setOnCellClickListener {
                        viewState.goToComicsDetailsScreen(it.id)
                    }
                    cells.add(cell)
                }
                viewState.showComicsList(cells)
            } else {
                viewState.showPlaceHolder()
            }
        }))
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}