package com.evaluation.fragment.detail

import com.evaluation.model.TickerItem
import com.evaluation.presenter.BasePresenter
import com.evaluation.presenter.BaseView

/**
 * @author Vladyslav Havrylenko
 * @since 15.04.2020
 */
interface DetailContract {

    interface View : BaseView {

        fun showDetail(tickerItem: List<TickerItem>)

    }

    interface Presenter : BasePresenter<View> {

        fun provideData(symbol: String)

        fun refreshData()

    }
}