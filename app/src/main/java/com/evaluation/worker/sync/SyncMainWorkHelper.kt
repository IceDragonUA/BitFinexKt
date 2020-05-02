package com.evaluation.worker.sync

import android.content.Context
import androidx.work.*
import com.evaluation.worker.UpdateMainWorker
import com.evaluation.worker.scheduler.SyncScheduler
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SyncMainWorkHelper @Inject constructor(private val context: Context) : SyncScheduler {

    override fun startSchedule(period: Long, timeUnit: TimeUnit) {
        val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
        val updateMenuWork = PeriodicWorkRequest.Builder(UpdateMainWorker::class.java, period, timeUnit)
                .setConstraints(constraints)
                .setBackoffCriteria(BackoffPolicy.LINEAR,
                    BACKOFF_DELAY_SECONDS, TimeUnit.SECONDS)
                .addTag(UPDATE_APP_WORK)
                .build()
        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            UPDATE_APP_PERIODIC,
                ExistingPeriodicWorkPolicy.REPLACE,
                updateMenuWork
        )
    }

    override fun startUpdate() {
        val updateMenuWork = OneTimeWorkRequest
                .Builder(UpdateMainWorker::class.java)
                .addTag(UPDATE_APP_WORK)
                .build()

        WorkManager.getInstance(context).beginUniqueWork(
            UPDATE_APP_UNIQUE,
                ExistingWorkPolicy.REPLACE,
                updateMenuWork
        ).enqueue()
    }

    companion object {

        const val UPDATE_APP_WORK = "update_app"

        private const val UPDATE_APP_PERIODIC = "update_app_periodic"

        private const val UPDATE_APP_UNIQUE = "update_app_unique"

        private const val BACKOFF_DELAY_SECONDS = 60L

    }

}