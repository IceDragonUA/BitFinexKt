package com.evaluation.fragment.main

import com.evaluation.model.TickerItem
import com.evaluation.presenter.BasePresenter
import com.evaluation.presenter.BaseView

/**
 * @author Vladyslav Havrylenko
 * @since 15.04.2020
 */
interface MainContract {

    interface View : BaseView {

        fun showList(tickerItem: List<TickerItem>)

    }

    interface Presenter : BasePresenter<View> {

        fun provideData()

        fun refreshData()

    }
}