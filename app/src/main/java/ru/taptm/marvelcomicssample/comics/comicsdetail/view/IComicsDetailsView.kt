package ru.taptm.marvelcomicssample.comics.comicsdetail.view

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.taptm.marvelcomicssample.base.IBaseView
import ru.taptm.marvelcomicssample.comics.comicsdetail.model.ComicsDetailsModel

interface IComicsDetailsView: IBaseView {

    @StateStrategyType(SingleStateStrategy::class)
    fun showComicsDetails(comicsModel: ComicsDetailsModel?)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showImageFullScreen(fullImageUrl: String?)

    fun showSelectedFavouritesStart()

    fun showDeselectedFavouritesStart()
}
