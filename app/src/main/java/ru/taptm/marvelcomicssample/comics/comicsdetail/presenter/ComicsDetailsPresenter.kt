package ru.taptm.marvelcomicssample.comics.comicsdetail.presenter

import android.widget.Toast
import com.arellomobile.mvp.InjectViewState
import ru.taptm.marvelcomicssample.R
import ru.taptm.marvelcomicssample.base.App
import ru.taptm.marvelcomicssample.base.BasePresenter
import ru.taptm.marvelcomicssample.comics.comicsdetail.model.ComicsDetailsModel
import ru.taptm.marvelcomicssample.comics.comicsdetail.view.IComicsDetailsView
import ru.taptm.marvelcomicssample.di.DI
import ru.taptm.marvelcomicssample.reposetory.IAppDataStorage
import ru.taptm.marvelcomicssample.reposetory.local.FavouritesData
import ru.taptm.marvelcomicssample.utils.RxSchedulersProvider
import java.util.*
import javax.inject.Inject

@InjectViewState
class ComicsDetailsPresenter: BasePresenter<IComicsDetailsView>() {
    private var comicsModel: ComicsDetailsModel? = null
    private var isFavourites: Boolean = false
    private var favouritesItem: FavouritesData? = null

    @Inject
    lateinit var repository: IAppDataStorage

    init {
        DI.componentManager().appComponent().inject(this)
    }

    fun loadData(comicsId: Int) {
        if (comicsId > 0) {
            disposable.add(
                    repository.getComicsDetails(comicsId)
                            .compose(RxSchedulersProvider.applyOpBeforeAndAfter(showLoading, hideLoading))
                            .doAfterSuccess { loadFavourites() }
                            .subscribe({ result ->
                                result.dataResponse.results?.let {
                                    val resultData = result.dataResponse.results
                                    comicsModel = ComicsDetailsModel(resultData)
                                    viewState.showComicsDetails(comicsModel)
                                }
                            }, { error ->
                                Toast.makeText(App.context(),
                                        String.format(App.context().getText(R.string.error_fail_connect).toString(), error.message),
                                        Toast.LENGTH_LONG).show()
                            }))
        }
    }

    private fun loadFavourites() {
        disposable.add(repository.getFavourites(comicsModel?.getComicsId() ?: 0)
                .subscribe {
                    if (it.isNotEmpty()) {
                        isFavourites = true
                        favouritesItem = it[0]
                        viewState.showSelectedFavouritesStart()
                    } else {
                        isFavourites = false
                        favouritesItem = null
                        viewState.showDeselectedFavouritesStart()
                    }
                })
    }

    fun onClickImage() {
        viewState.showImageFullScreen(comicsModel?.getFullImageUrl())
    }

    fun onClickFavourites() {
        if (isFavourites) {
            favouritesItem?.let {
                repository.deleteFavourites(it)
            }
        } else {
            comicsModel?.let {
                repository.insertFavourites(FavouritesData(
                        it.getComicsId(),
                        Date().time,
                        title = it.getTitle(),
                        imageUrl = it.getImageUrl()))
            }
        }
    }
}