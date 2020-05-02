package com.evaluation.dagger

import android.app.Application
import com.evaluation.App
import com.evaluation.activity.MainActivity
import com.evaluation.dagger.detail.DetailComponent
import com.evaluation.dagger.main.MainComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [
    AndroidModule::class,
    DataModule::class])
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun app(application: Application): Builder

        fun build(): AppComponent

    }

    fun mainComponent(): MainComponent

    fun detailComponent(): DetailComponent
}