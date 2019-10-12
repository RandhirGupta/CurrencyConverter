package com.cyborg.currencyconverter.data.usecase

import com.cyborg.currencyconverter.data.entity.CurrenciesEntity
import com.cyborg.currencyconverter.data.executor.BaseSchedulerProvider
import com.cyborg.currencyconverter.data.local.LocalSource
import com.cyborg.currencyconverter.data.model.Currencies
import com.cyborg.currencyconverter.data.repository.CurrenciesRepository
import com.cyborg.currencyconverter.presentation.common.toCurrenciesEntity
import io.reactivex.Flowable
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FetchCurrenciesUseCase @Inject constructor(
    private val currenciesRepository: CurrenciesRepository,
    private val schedulerProvider: BaseSchedulerProvider,
    private val localSource: LocalSource
) {

    fun getCurrenciesFromNetwork(baseCurrency: String): Flowable<Currencies> {
        return currenciesRepository.getCurrenciesFromNetwork(baseCurrency)
            .subscribeOn(schedulerProvider.io())
            .onBackpressureBuffer()
            .observeOn(schedulerProvider.ui()).repeatWhen {
                Flowable.timer(2, TimeUnit.SECONDS).repeat()
            }.doOnNext {
                localSource.insertCurrencies(it.toCurrenciesEntity())
            }
    }

    fun getCurrenciesFromLocal(baseCurrency: String): Flowable<CurrenciesEntity> {
        return currenciesRepository.getCurrenciesFromLocal(baseCurrency)
    }
}