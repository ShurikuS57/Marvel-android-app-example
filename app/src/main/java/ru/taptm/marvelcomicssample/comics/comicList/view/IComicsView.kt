package ru.taptm.marvelcomicssample.comics.comicList.view

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.taptm.marvelcomicssample.base.IBaseView
import ru.taptm.marvelcomicssample.comics.comicList.adapter.ComicsCell


interface IComicsView: IBaseView {
    fun showContent(cells: ArrayList<ComicsCell>)

    fun clearComicsList()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun goToComicsDetailsScreen(id: Int?)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun goToFavouritesScreen()

    fun showFavouritesFab()

    fun hideFavouritesFab()
}