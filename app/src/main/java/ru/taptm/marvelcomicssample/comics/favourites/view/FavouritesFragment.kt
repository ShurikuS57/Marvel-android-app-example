package ru.taptm.marvelcomicssample.comics.favourites.view

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_favourites.*
import ru.taptm.marvelcomicssample.R
import ru.taptm.marvelcomicssample.base.BaseFragment
import ru.taptm.marvelcomicssample.comics.ComicsListActivity
import ru.taptm.marvelcomicssample.comics.comicList.adapter.ComicsCell
import ru.taptm.marvelcomicssample.comics.comicsdetail.view.ComicsDetailsFragment
import ru.taptm.marvelcomicssample.comics.favourites.presenter.FavouritesPresenter
import ru.taptm.marvelcomicssample.ui.SpacesItemDecoration

class FavouritesFragment: BaseFragment(), IFavouritesView {
    @InjectPresenter
    lateinit var presenter: FavouritesPresenter

    override fun setUpView() {
        presenter.loadData()
        setupComicsListView()
        setupActionBar()
    }

    private fun setupActionBar() {
        val actionBar = (activity as ComicsListActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.title = getString(R.string.title_favourites)
    }

    private fun setupComicsListView() {
        list_comics.addItemDecoration(SpacesItemDecoration(resources.getDimension(R.dimen.grid_padding).toInt()))
    }

    override fun showComicsList(cells: ArrayList<ComicsCell>) {
        frame_place_holder.visibility = View.GONE
        list_comics.removeAllCells()
        list_comics.addCells(cells)
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun getLayoutID(): Int {
        return R.layout.fragment_favourites
    }

    override fun showPlaceHolder() {
        frame_place_holder.visibility = View.VISIBLE
        list_comics.removeAllCells()
    }

    override fun goToComicsDetailsScreen(id: Int?) {
        val bundle = Bundle()
        bundle.putInt(ComicsDetailsFragment.ARG_COMICS_ID, id ?: 0)

        NavHostFragment.findNavController(this)
                .navigate(R.id.comicsDetailsFragment, bundle)
    }

}