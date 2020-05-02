package com.evaluation.network

import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Vladyslav Havrylenko
 * @since 01.05.2020
 */
@Singleton
class RestClient @Inject constructor() {

    val httpClient: OkHttpClient

    init {
        val intLogging = HttpLoggingInterceptor()
        intLogging.level = HttpLoggingInterceptor.Level.BODY
        httpClient = OkHttpClient().newBuilder()
            .addInterceptor(intLogging)
            .addNetworkInterceptor(StethoInterceptor())
            .build()
    }

}