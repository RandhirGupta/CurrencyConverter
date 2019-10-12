package com.cyborg.currencyconverter.data.repository

import com.cyborg.currencyconverter.data.entity.CurrenciesEntity
import com.cyborg.currencyconverter.data.model.Currencies
import io.reactivex.Flowable

interface CurrenciesRepository {

    fun getCurrenciesFromNetwork(baseCurrency: String): Flowable<Currencies>

    fun getCurrenciesFromLocal(baseCurrency: String): Flowable<CurrenciesEntity>
}