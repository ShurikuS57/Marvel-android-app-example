package ru.taptm.marvelcomicssample.comics.comicList.presenter

import android.support.v4.widget.SwipeRefreshLayout
import com.arellomobile.mvp.InjectViewState
import com.jaychang.srv.OnLoadMoreListener
import io.reactivex.disposables.CompositeDisposable
import ru.taptm.marvelcomicssample.R
import ru.taptm.marvelcomicssample.base.App
import ru.taptm.marvelcomicssample.base.BasePresenter
import ru.taptm.marvelcomicssample.comics.comicList.adapter.ComicsCell
import ru.taptm.marvelcomicssample.comics.comicList.view.IComicsView
import ru.taptm.marvelcomicssample.reposetory.AppDataStorageImpl
import ru.taptm.marvelcomicssample.reposetory.local.RoomDatabaseStorage
import ru.taptm.marvelcomicssample.reposetory.network.response.ComicsResponse
import ru.taptm.marvelcomicssample.reposetory.network.service.ServiceFabric


@InjectViewState
open class ComicsPresenter : BasePresenter<IComicsView>(), OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    private val disposable: CompositeDisposable
        get() = CompositeDisposable()

    private val repository = AppDataStorageImpl(ServiceFabric.getApiService(), RoomDatabaseStorage.getInstance().favouritesDataDao())
    private var currentOffsetPage = 0
    private var totalPage = 0
    private var isFirstLoad = true
    private val cells = ArrayList<ComicsCell>()

    fun onSetupView() {
        if (isFirstLoad) {
            loadMoreData()
        }
        checkFavouritesComics()
    }

    private fun loadMoreData() {
        checkIsFirstLoadData()
        disposable.add(repository.getComics(currentOffsetPage)
                .subscribe({ result ->
                    run(hideLoading)
                    currentOffsetPage += result.dataResponse.count?: 0
                    totalPage = result.dataResponse.total?: 0
                    prepareComicsList(result)
                }, { e ->
                    viewState.hideLoading()
                    viewState.showToast(App.context().resources.getString(R.string.error_load_comics_list) + " : ${e.message}")
                }))
    }

    private fun checkFavouritesComics() {
        disposable.add(repository.getAllFavourites().subscribe({ result ->
            if (result.isNotEmpty()) {
                viewState.showFavouritesFab()
            } else {
                viewState.hideFavouritesFab()
            }
        }))
    }

    private fun checkIsFirstLoadData() {
        if (isFirstLoad) {
            run(showLoading)
            isFirstLoad = false
        }
    }

    override fun shouldLoadMore(): Boolean {
        return currentOffsetPage < totalPage
    }

    override fun onLoadMore() {
        loadMoreData()
    }

    override fun onRefresh() {
        currentOffsetPage = 0
        cells.clear()
        viewState.clearComicsList()
        loadMoreData()
    }

    private fun prepareComicsList(result: ComicsResponse) {
        result.dataResponse.results?.let {
            val resultData = result.dataResponse.results

            resultData?.forEach {
                val cell = ComicsCell(it)
                cell.setOnCellClickListener {
                    viewState.goToComicsDetailsScreen(it.id)
                }
                cells.add(cell)
            }
            viewState.clearComicsList()
            viewState.showContent(cells)
        }
    }

    override fun destroyView(view: IComicsView?) {
        super.destroyView(view)
        disposable.clear()
    }

    fun onFavouritesFabClick() {
        viewState.goToFavouritesScreen()
    }
}