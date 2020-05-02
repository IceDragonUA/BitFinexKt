package com.evaluation.dagger.detail

import com.evaluation.fragment.detail.DetailContract
import com.evaluation.fragment.detail.DetailPresenter
import com.evaluation.repository.detail.DetailRepository
import com.evaluation.repository.detail.DetailRepositoryImpl
import com.evaluation.worker.scheduler.SyncScheduler
import com.evaluation.worker.sync.SyncDetailWorkHelper
import dagger.Binds
import dagger.Module

@Module
abstract class DetailModule {

    @Binds
    @DetailScope
    abstract fun repository(detailRepositoryImpl: DetailRepositoryImpl): DetailRepository

    @Binds
    @DetailScope
    abstract fun presenter(detailPresenter: DetailPresenter): DetailContract.Presenter

    @Binds
    @DetailScope
    abstract fun synccheduler(syncConfigWorkHelper: SyncDetailWorkHelper): SyncScheduler

}