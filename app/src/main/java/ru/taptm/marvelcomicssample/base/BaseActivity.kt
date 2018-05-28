package ru.taptm.marvelcomicssample.base

import android.os.Bundle
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity

abstract class BaseActivity : MvpAppCompatActivity(), IBaseView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutID())
        setUpView()
    }

    protected abstract fun getLayoutID(): Int

    open fun setUpView() {}

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}