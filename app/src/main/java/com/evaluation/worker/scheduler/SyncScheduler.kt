package com.evaluation.worker.scheduler

import java.util.concurrent.TimeUnit

interface SyncScheduler {

    fun startSchedule(period: Long, timeUnit: TimeUnit)

    fun startUpdate()

}