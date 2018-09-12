package ru.taptm.marvelcomicssample.comics.comicsdetail.presenter

import com.arellomobile.mvp.InjectViewState
import ru.taptm.marvelcomicssample.R
import ru.taptm.marvelcomicssample.base.BasePresenter
import ru.taptm.marvelcomicssample.comics.comicsdetail.model.ComicsDetailsModel
import ru.taptm.marvelcomicssample.comics.comicsdetail.view.IComicsDetailsView
import ru.taptm.marvelcomicssample.di.DI
import ru.taptm.marvelcomicssample.interactor.ComicsInteractor
import ru.taptm.marvelcomicssample.interactor.FavouritesInteractor
import ru.taptm.marvelcomicssample.reposetory.local.FavouritesData
import ru.taptm.marvelcomicssample.utils.RxSchedulersProvider
import java.util.*
import javax.inject.Inject

@InjectViewState
class ComicsDetailsPresenter : BasePresenter<IComicsDetailsView>() {
    @Inject
    lateinit var favouritesInteractor: FavouritesInteractor
    @Inject
    lateinit var comicsInteractor: ComicsInteractor

    private var comicsModel: ComicsDetailsModel? = null
    private var isFavourites: Boolean = false
    private var favouritesItem: FavouritesData? = null

    init {
        DI.componentManager().appComponent().inject(this)
    }

    fun loadData(comicsId: Int) {
        disposable.add(comicsInteractor.loadComicsDetails(comicsId)
                .compose(RxSchedulersProvider.applyOpBeforeAndAfter(showLoading, hideLoading))
                .doAfterSuccess { subscribeChangeFavourites() }
                .subscribe({ result ->
                    if (result != null) {
                        this.comicsModel = result
                        viewState.showComicsDetails(comicsModel)
                    }
                }, { error ->
                    viewState.showToastFormat(R.string.error_fail_connect, error.message)
                }))
    }

    private fun subscribeChangeFavourites() {
        disposable.add(favouritesInteractor.loadFavourites(comicsModel?.getComicsId() ?: 0)
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

    fun onClickFullImage() {
        viewState.showImageFullScreen(comicsModel?.getFullImageUrl())
    }

    fun onClickFavourites() {
        if (isFavourites) {
            favouritesItem?.let {
                favouritesInteractor.deleteFavourites(it)
            }
        } else {
            comicsModel?.let {
                favouritesInteractor.insertFavourites(FavouritesData(
                        comicsId = it.getComicsId(),
                        date = Date().time,
                        title = it.getTitle(),
                        imageUrl = it.getImageUrl()))
            }
        }
    }
}