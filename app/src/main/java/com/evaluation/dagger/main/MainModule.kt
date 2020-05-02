package com.evaluation.dagger.main

import com.evaluation.fragment.main.MainContract
import com.evaluation.fragment.main.MainPresenter
import com.evaluation.repository.main.MainRepository
import com.evaluation.repository.main.MainRepositoryImpl
import com.evaluation.worker.scheduler.SyncScheduler
import com.evaluation.worker.sync.SyncMainWorkHelper
import dagger.Binds
import dagger.Module

@Module
abstract class MainModule {

    @Binds
    @MainScope
    abstract fun repository(mainRepositoryImpl: MainRepositoryImpl): MainRepository

    @Binds
    @MainScope
    abstract fun presenter(mainPresenter: MainPresenter): MainContract.Presenter

    @Binds
    @MainScope
    abstract fun syncScheduler(syncConfigWorkHelper: SyncMainWorkHelper): SyncScheduler

}