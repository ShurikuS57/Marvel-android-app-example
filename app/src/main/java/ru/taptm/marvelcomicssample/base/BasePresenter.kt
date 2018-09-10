package ru.taptm.marvelcomicssample.base

import com.arellomobile.mvp.MvpPresenter
import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<ViewClass : IBaseView> : MvpPresenter<ViewClass>() {
    protected val disposable = CompositeDisposable()

    protected val showLoading = { viewState.showLoading() }
    protected val hideLoading = { viewState.hideLoading() }

    override fun destroyView(view: ViewClass) {
        super.destroyView(view)
        disposable.clear()
    }
}