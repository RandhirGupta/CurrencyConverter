package com.cyborg.currencyconverter.data.local

import com.cyborg.currencyconverter.data.entity.CurrenciesEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Singleton

@Singleton
class LocalSourceImpl(private val currenciesDatabase: CurrenciesDatabase) : LocalSource {

    override fun insertCurrencies(currenciesEntity: CurrenciesEntity): Completable {
        return Completable.fromCallable {
            currenciesDatabase.currenciesDao().insertCurrencies(currenciesEntity)
        }
    }

    override fun getCurrencies(baseCurrency: String): Flowable<CurrenciesEntity> {
        return currenciesDatabase.currenciesDao().getCurrencies(baseCurrency).onErrorReturn {
            null
        }
    }
}