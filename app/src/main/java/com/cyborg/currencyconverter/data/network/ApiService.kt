package com.cyborg.currencyconverter.data.network

import com.cyborg.currencyconverter.data.entity.Currencies
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("latest")
    fun getCurrencies(@Query("base") baseCurrency: String): Flowable<Currencies>
}