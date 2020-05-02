package com.evaluation.dagger

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AndroidModule {

    @Provides
    @JvmStatic
    @Singleton
    fun context(app: Application): Context {
        return app.applicationContext
    }

}