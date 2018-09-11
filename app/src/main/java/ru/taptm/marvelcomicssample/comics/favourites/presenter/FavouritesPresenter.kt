package ru.taptm.marvelcomicssample.comics.favourites.presenter

import com.arellomobile.mvp.InjectViewState
import ru.taptm.marvelcomicssample.base.BasePresenter
import ru.taptm.marvelcomicssample.comics.favourites.view.IFavouritesView
import ru.taptm.marvelcomicssample.di.DI
import ru.taptm.marvelcomicssample.interactor.FavouritesInteractor
import javax.inject.Inject

@InjectViewState
class FavouritesPresenter : BasePresenter<IFavouritesView>() {
    @Inject
    lateinit var favouritesInteractor: FavouritesInteractor

    init {
        DI.componentManager().appComponent().inject(this)
    }

    fun loadData() {
        disposable.add(favouritesInteractor
                .loadAllFavourites()
                .subscribe { cellComicsList ->
                    if (cellComicsList.isNotEmpty()) {
                        cellComicsList.forEach { cellComics ->
                            cellComics.setOnCellClickListener {
                                viewState.goToComicsDetailsScreen(it.id)
                            }
                        }
                        viewState.showComicsList(cellComicsList)
                    } else {
                        viewState.showPlaceHolder()
                    }
                })
    }
}