package ru.taptm.marvelcomicssample.comics

import android.view.MenuItem
import ru.taptm.marvelcomicssample.R
import ru.taptm.marvelcomicssample.base.BaseActivity


class ComicsListActivity : BaseActivity() {

    override fun setUpView() {
        supportActionBar?.title = resources.getString(R.string.title_comics_list)
    }

    override fun getLayoutID(): Int {
        return R.layout.activity_comics_list
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }
}
