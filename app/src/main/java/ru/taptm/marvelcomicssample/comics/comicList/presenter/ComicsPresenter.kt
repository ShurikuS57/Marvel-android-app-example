package ru.taptm.marvelcomicssample.comics.comicList.presenter

import android.support.v4.widget.SwipeRefreshLayout
import com.arellomobile.mvp.InjectViewState
import com.jaychang.srv.OnLoadMoreListener
import ru.taptm.marvelcomicssample.R
import ru.taptm.marvelcomicssample.base.App
import ru.taptm.marvelcomicssample.base.BasePresenter
import ru.taptm.marvelcomicssample.comics.comicList.view.IComicsView
import ru.taptm.marvelcomicssample.di.DI
import ru.taptm.marvelcomicssample.interactor.ComicsInteractor
import javax.inject.Inject


@InjectViewState
class ComicsPresenter : BasePresenter<IComicsView>(), OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    @Inject
    lateinit var comicsIntaractor: ComicsInteractor

    private var isFirstLoad = true

    init {
        DI.componentManager().appComponent().inject(this)
    }

    fun onSetupView() {
        if (isFirstLoad) {
            loadMoreData()
        }
        checkFavouritesComics()
    }

    private fun loadMoreData() {
        checkIsFirstLoadData()
        disposable.add(comicsIntaractor
                .getComics()
                .subscribe({ result ->
                    run(hideLoading)
                    result.forEach {
                        it.setOnCellClickListener { comics ->
                            viewState.goToComicsDetailsScreen(comics.id)
                        }
                    }
                    viewState.clearComicsList()
                    viewState.showContent(result)
                }, { e ->
                    viewState.hideLoading()
                    viewState.showToast(App.context().resources.getString(R.string.error_load_comics_list) + " : ${e.message}")
                }))
    }

    private fun checkFavouritesComics() {
        disposable.add(comicsIntaractor
                .getAllFavourites()
                .subscribe { result ->
                    if (result.isNotEmpty()) {
                        viewState.showFavouritesFab()
                    } else {
                        viewState.hideFavouritesFab()
                    }
                })
    }

    private fun checkIsFirstLoadData() {
        if (isFirstLoad) {
            run(showLoading)
            isFirstLoad = false
        }
    }

    override fun shouldLoadMore(): Boolean {
        return comicsIntaractor.isShouldLoadMore()
    }

    override fun onLoadMore() {
        loadMoreData()
    }

    override fun onRefresh() {
        comicsIntaractor.clearComicsData()
        viewState.clearComicsList()
        loadMoreData()
    }

    fun onFavouritesFabClick() {
        viewState.goToFavouritesScreen()
    }
}