package com.evaluation.worker

import android.content.Context
import androidx.work.RxWorker
import androidx.work.WorkerParameters
import com.evaluation.App
import com.evaluation.event.bus.UpdateMainDomainBus
import io.reactivex.Single
import javax.inject.Inject

class UpdateMainWorker(context: Context, params: WorkerParameters) : RxWorker(context, params) {

    @Inject
    lateinit var updateDomainBus: UpdateMainDomainBus

    init {
        App.mainComponent?.inject(this)
    }

    override fun createWork(): Single<Result> {
        updateDomainBus.postUpdated()
        return Single.just(Result.retry())
    }

}