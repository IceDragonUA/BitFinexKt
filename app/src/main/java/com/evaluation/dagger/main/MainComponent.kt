package com.evaluation.dagger.main

import com.evaluation.fragment.main.MainFragment
import com.evaluation.worker.UpdateMainWorker
import dagger.Subcomponent

@Subcomponent(modules = [MainModule::class])
@MainScope
interface MainComponent {

    fun inject (fragment: MainFragment)

    fun inject(syncWorkHelper: UpdateMainWorker)

}