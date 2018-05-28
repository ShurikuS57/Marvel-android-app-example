package ru.taptm.marvelcomicssample.base

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface IBaseView : MvpView {
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showToast(message: String)
    fun showLoading()
    fun hideLoading()
}