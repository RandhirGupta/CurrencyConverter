package com.cyborg.currencyconverter.data.repository

import com.cyborg.currencyconverter.data.entity.Currencies
import com.cyborg.currencyconverter.data.network.ApiService
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrenciesRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : CurrenciesRepository {

    override fun getCurrencies(baseCurrency: String): Flowable<Currencies> =
        apiService.getCurrencies(baseCurrency)
}