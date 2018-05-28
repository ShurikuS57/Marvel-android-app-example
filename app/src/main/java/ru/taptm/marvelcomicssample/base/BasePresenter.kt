package ru.taptm.marvelcomicssample.base

import com.arellomobile.mvp.MvpPresenter

abstract class BasePresenter<ViewClass : IBaseView> : MvpPresenter<ViewClass>() {
    protected val showLoading = { viewState.showLoading() }
    protected val hideLoading = { viewState.hideLoading() }
}