package com.evaluation.network

import com.evaluation.model.TickerList
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {

    @GET("v2/tickers/")
    fun getTickerList(@Query("symbols") type: String?): Single<TickerList>

}