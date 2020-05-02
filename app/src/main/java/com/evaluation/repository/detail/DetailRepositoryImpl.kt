package com.evaluation.repository.detail

import com.evaluation.model.TickerItem
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
class DetailRepositoryImpl @Inject constructor(
    private val appRest: RestAdapter,
    private val appDao: AppDao,
    private val syncScheduler: SyncScheduler
) : DetailRepository {

    override fun getTickerList(symbol: String?): Flowable<List<TickerItem>> {
        return Single.mergeDelayError(
            appRest.api.getTickerList(symbol)
                .doOnSuccess {
                    it?.mResultList?.let { list -> appDao.insertTickerList(list) }
                }
                .flatMap {
                    appDao.getTickerArchivedList(symbol)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()),
            appDao.getTickerArchivedList(symbol)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    override fun syncRegisterData(intervalMinutes: Int) {
        syncScheduler.startSchedule(intervalMinutes.toLong(), TimeUnit.MINUTES)
    }

}
