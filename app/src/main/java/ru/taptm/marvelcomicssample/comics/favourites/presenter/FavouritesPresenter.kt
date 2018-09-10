package ru.taptm.marvelcomicssample.comics.favourites.presenter

import android.net.Uri
import com.arellomobile.mvp.InjectViewState
import io.reactivex.disposables.CompositeDisposable
import ru.taptm.marvelcomicssample.base.BasePresenter
import ru.taptm.marvelcomicssample.comics.comicList.adapter.ComicsCell
import ru.taptm.marvelcomicssample.comics.favourites.view.IFavouritesView
import ru.taptm.marvelcomicssample.di.DI
import ru.taptm.marvelcomicssample.reposetory.IAppDataStorage
import ru.taptm.marvelcomicssample.reposetory.network.response.ImageResponse
import ru.taptm.marvelcomicssample.reposetory.network.response.ResultResponse
import javax.inject.Inject

@InjectViewState
class FavouritesPresenter: BasePresenter<IFavouritesView>() {
    private val disposable: CompositeDisposable
        get() = CompositeDisposable()

    @Inject
    lateinit var repository: IAppDataStorage

    init {
        DI.componentManager().appComponent().inject(this)
    }

    fun loadData() {
        disposable.add(repository.getAllFavourites().subscribe {
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
                    cell.setOnCellClickListener {result ->
                        viewState.goToComicsDetailsScreen(result.id)
                    }
                    cells.add(cell)
                }
                viewState.showComicsList(cells)
            } else {
                viewState.showPlaceHolder()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}