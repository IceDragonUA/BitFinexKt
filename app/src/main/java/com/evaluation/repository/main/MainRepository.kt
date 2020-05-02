package com.evaluation.repository.main

import com.evaluation.model.TickerItem
import io.reactivex.Flowable

/**
 * @author Vladyslav Havrylenko
 * @since 01.05.2020
 */
interface MainRepository {

    fun getTickerList(): Flowable<List<TickerItem>>

    fun syncRegisterData(intervalMinutes: Int)

}