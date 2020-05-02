package com.evaluation.worker

import android.content.Context
import androidx.work.RxWorker
import androidx.work.WorkerParameters
import com.evaluation.App
import com.evaluation.event.bus.UpdateDetailDomainBus
import io.reactivex.Single
import javax.inject.Inject

class UpdateDetailWorker(context: Context, params: WorkerParameters) : RxWorker(context, params) {

    @Inject
    lateinit var updateDomainBus: UpdateDetailDomainBus

    init {
        App.detailComponent?.inject(this)
    }

    override fun createWork(): Single<Result> {
        updateDomainBus.postUpdated()
        return Single.just(Result.retry())
    }

}