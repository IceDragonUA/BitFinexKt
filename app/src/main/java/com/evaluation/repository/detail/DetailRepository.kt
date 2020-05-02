package com.evaluation.repository.detail

import com.evaluation.model.TickerItem
import io.reactivex.Flowable

/**
 * @author Vladyslav Havrylenko
 * @since 01.05.2020
 */
interface DetailRepository {

    fun getTickerList(symbol: String?): Flowable<List<TickerItem>>

    fun syncRegisterData(intervalMinutes: Int)

}