package com.evaluation.dagger.detail

import com.evaluation.fragment.detail.DetailFragment
import com.evaluation.worker.UpdateDetailWorker
import com.evaluation.worker.UpdateMainWorker
import dagger.Subcomponent

@Subcomponent(modules = [DetailModule::class])
@DetailScope
interface DetailComponent {

    fun inject (fragment: DetailFragment)

    fun inject(syncWorkHelper: UpdateDetailWorker)
}