package com.evaluation.repository.main

import com.evaluation.model.TickerItem
import com.evaluation.model.TickerList
import com.evaluation.network.RestAdapter
import com.evaluation.room.dao.AppDao
import com.evaluation.worker.scheduler.SyncScheduler
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject


/**
 * @author Vladyslav Havrylenko
 * @since 01.05.2020
 */
class MainRepositoryImpl @Inject constructor(
    private val appRest: RestAdapter,
    private val appDao: AppDao,
    private val syncScheduler: SyncScheduler
) : MainRepository {

    override fun getTickerList(): Flowable<List<TickerItem>> {
        return Single.mergeDelayError(
            appRest.api.getTickerList("ALL")
                .doOnSuccess {
                    it?.mResultList?.let { list -> appDao.insertTickerList(list) }
                }
                .flatMap {
                    appDao.getTickerList()
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()),
            appDao.getTickerList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    override fun syncRegisterData(intervalMinutes: Int) {
        syncScheduler.startSchedule(intervalMinutes.toLong(), TimeUnit.MINUTES)
    }

}
