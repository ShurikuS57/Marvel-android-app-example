package ru.taptm.marvelcomicssample.comics.comicsdetail.view

import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_comics_details.*
import kotlinx.android.synthetic.main.layout_placeholder_progress.*
import ru.taptm.marvelcomicssample.R
import ru.taptm.marvelcomicssample.base.BaseFragment
import ru.taptm.marvelcomicssample.comics.ComicsListActivity
import ru.taptm.marvelcomicssample.comics.comicsdetail.model.ComicsDetailsModel
import ru.taptm.marvelcomicssample.comics.comicsdetail.presenter.ComicsDetailsPresenter
import ru.taptm.marvelcomicssample.ui.imagefullscreen.PhotoFullPopupWindow
import ru.taptm.marvelcomicssample.utils.ImageHelper

class ComicsDetailsFragment: BaseFragment(), IComicsDetailsView {

    companion object {
        var ARG_COMICS_ID = "ARG_COMICS_ID"
    }

    @InjectPresenter
    lateinit var presenter: ComicsDetailsPresenter

    override fun setUpView() {
        arguments?.let {
            presenter.loadData(it.getInt(ARG_COMICS_ID, 0))
        }
        imageView.setOnClickListener {
            presenter.onClickImage()
        }
        image_favourites.setOnClickListener {
            presenter.onClickFavourites()
        }

        setupActionBar()
    }

    private fun setupActionBar() {
        val actionBar = (activity as ComicsListActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.title = getString(R.string.title_comics_list)
    }

    override fun showLoading() {
        root_progress_placeholder?.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        root_progress_placeholder?.visibility = View.GONE
    }

    override fun getLayoutID(): Int {
        return R.layout.fragment_comics_details
    }

    override fun showImageFullScreen(fullImageUrl: String?) {
        if (context != null && fullImageUrl != null && view != null) {
            PhotoFullPopupWindow(context, R.layout.popup_photo_full, view, fullImageUrl, null)
        }
    }

    override fun showComicsDetails(comicsModel: ComicsDetailsModel?) {
        ImageHelper.setImageWithPlaceHolder(comicsModel?.getImageUrl()?:"", imageView)
        description.text = comicsModel?.getDescription()
        title.text = comicsModel?.getTitle()
        authors.text = comicsModel?.getCreators()
    }

    override fun showSelectedFavouritesStart() {
        image_favourites.setImageResource(R.drawable.ic_star)
    }

    override fun showDeselectedFavouritesStart() {
        image_favourites.setImageResource(R.drawable.ic_star_border)
    }
}
