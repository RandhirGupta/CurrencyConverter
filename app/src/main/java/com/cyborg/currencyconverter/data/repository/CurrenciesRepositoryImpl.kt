package com.cyborg.currencyconverter.data.repository

import com.cyborg.currencyconverter.data.entity.CurrenciesEntity
import com.cyborg.currencyconverter.data.local.LocalSource
import com.cyborg.currencyconverter.data.model.Currencies
import com.cyborg.currencyconverter.data.network.ApiService
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrenciesRepositoryImpl @Inject constructor(
    private val apiService: ApiService, private val localSource: LocalSource
) : CurrenciesRepository {

    override fun getCurrenciesFromNetwork(baseCurrency: String): Flowable<Currencies> =
        apiService.getCurrencies(baseCurrency)

    override fun getCurrenciesFromLocal(baseCurrency: String): Flowable<CurrenciesEntity> =
        localSource.getCurrencies(baseCurrency)
}