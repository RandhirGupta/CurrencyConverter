package com.cyborg.currencyconverter.data.repository

import com.cyborg.currencyconverter.data.entity.Currencies
import io.reactivex.Flowable

interface CurrenciesRepository {

    fun getCurrencies(baseCurrency: String): Flowable<Currencies>
}