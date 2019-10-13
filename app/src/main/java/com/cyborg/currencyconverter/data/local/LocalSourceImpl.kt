package com.cyborg.currencyconverter.data.local

import android.util.Log
import com.cyborg.currencyconverter.data.entity.CurrenciesEntity
import com.cyborg.currencyconverter.data.executor.BaseSchedulerProvider
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Singleton


@Singleton
class LocalSourceImpl(
    private val currenciesDatabase: CurrenciesDatabase,
    private val schedulerProvider: BaseSchedulerProvider
) : LocalSource {

    override fun insertCurrencies(currenciesEntity: CurrenciesEntity): Completable {
        return Completable.fromCallable {
            currenciesDatabase.currenciesDao().insertCurrencies(currenciesEntity)
        }.onErrorComplete {
            Log.d("NNN", it.localizedMessage)
            true
        }.subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
    }

    override fun getCurrencies(baseCurrency: String): Flowable<CurrenciesEntity> {
        return currenciesDatabase.currenciesDao().getCurrencies(baseCurrency).onErrorReturn {
            null
        }
    }
}