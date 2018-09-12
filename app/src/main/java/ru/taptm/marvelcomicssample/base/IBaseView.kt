package ru.taptm.marvelcomicssample.base

import android.support.annotation.StringRes
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface IBaseView : MvpView {
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showToast(message: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showToastFormat(@StringRes stringFormatRes: Int, stringConcat: String?)

    fun showLoading()

    fun hideLoading()
}