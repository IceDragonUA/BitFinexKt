package com.evaluation.network

import com.evaluation.model.TickerList
import com.evaluation.utils.BASE_URL
import com.evaluation.utils.CustomDeserializer
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RestAdapter @Inject constructor(httpClient: OkHttpClient) {

    val api: RestApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().registerTypeAdapter(TickerList::class.java, CustomDeserializer()).create()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(httpClient)
        .build()
        .create(RestApi::class.java)

}