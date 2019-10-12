package com.cyborg.currencyconverter.data.local

import com.cyborg.currencyconverter.data.entity.CurrenciesEntity
import io.reactivex.Completable
import io.reactivex.Flowable

interface LocalSource {

    fun insertCurrencies(currenciesEntity: CurrenciesEntity): Completable

    fun getCurrencies(baseCurrency: String): Flowable<CurrenciesEntity>
}