package ru.taptm.marvelcomicssample.comics.favourites.view

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.taptm.marvelcomicssample.base.IBaseView
import ru.taptm.marvelcomicssample.comics.comicList.adapter.ComicsCell

interface IFavouritesView: IBaseView {

    @StateStrategyType(SingleStateStrategy::class)
    fun showComicsList(cells: ArrayList<ComicsCell>)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun goToComicsDetailsScreen(id: Int?)

    fun showPlaceHolder()
}