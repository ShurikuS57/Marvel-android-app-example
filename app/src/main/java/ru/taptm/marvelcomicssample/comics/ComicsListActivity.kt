package ru.taptm.marvelcomicssample.comics

import android.view.MenuItem
import android.widget.Toast
import ru.taptm.marvelcomicssample.R
import ru.taptm.marvelcomicssample.base.BaseActivity


class ComicsListActivity : BaseActivity() {
    override val activityLayout = R.layout.activity_comics_list

    override fun setUpView() {
        supportActionBar?.title = resources.getString(R.string.title_comics_list)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }
}
