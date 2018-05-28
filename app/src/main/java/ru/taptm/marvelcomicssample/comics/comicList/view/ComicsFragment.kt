package ru.taptm.marvelcomicssample.comics.comicList.view

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_comics.*
import kotlinx.android.synthetic.main.layout_placeholder_progress.*
import ru.taptm.marvelcomicssample.R
import ru.taptm.marvelcomicssample.base.BaseFragment
import ru.taptm.marvelcomicssample.comics.ComicsListActivity
import ru.taptm.marvelcomicssample.comics.comicList.adapter.ComicsCell
import ru.taptm.marvelcomicssample.comics.comicList.presenter.ComicsPresenter
import ru.taptm.marvelcomicssample.comics.comicsdetail.view.ComicsDetailsFragment
import ru.taptm.marvelcomicssample.ui.SpacesItemDecoration
import java.util.*

class ComicsFragment: BaseFragment(), IComicsView {
    @InjectPresenter
    lateinit var presenter: ComicsPresenter

    override fun setUpView() {
        setupComicsListView()
        setupSwipeRefresh()
        setupFab()
        presenter.onSetupView()
        setupActionBar()
    }

    private fun setupActionBar() {
        val actionBar = (activity as ComicsListActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(false)
        actionBar?.title = getString(R.string.title_comic_details)
    }

    private fun setupFab() {
        fab.setOnClickListener{
            presenter.onFavouritesFabClick()
        }
    }

    private fun setupSwipeRefresh() {
        swipe_refresh.setOnRefreshListener(presenter)
    }

    private fun setupComicsListView() {
        list_comics.addItemDecoration(SpacesItemDecoration(resources.getDimension(R.dimen.grid_padding).toInt()))
        list_comics.autoLoadMoreThreshold = 4
        list_comics.setOnLoadMoreListener(presenter)
    }

    override fun showContent(cells: ArrayList<ComicsCell>) {
        list_comics.addCells(cells)
    }
    override fun getLayoutID(): Int {
        return R.layout.fragment_comics
    }

    override fun showLoading() {
        root_progress_placeholder?.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        root_progress_placeholder?.visibility = View.GONE
        swipe_refresh?.isRefreshing = false
    }

    override fun clearComicsList() {
        list_comics.removeAllCells()
    }

    override fun goToComicsDetailsScreen(id: Int?) {
        val bundle = Bundle()
        bundle.putInt(ComicsDetailsFragment.ARG_COMICS_ID, id?:0)

        findNavController(this)
                .navigate(R.id.comicsDetailsFragment, bundle)
    }

    override fun goToFavouritesScreen() {
        findNavController(this)
                .navigate(R.id.favouritesFragment)
    }

    override fun showFavouritesFab() {
        fab.show()
    }

    override fun hideFavouritesFab() {
        fab.hide()
    }
}