package com.cyborg.currencyconverter.data.usecase

import com.cyborg.currencyconverter.data.entity.Currencies
import com.cyborg.currencyconverter.data.executor.BaseSchedulerProvider
import com.cyborg.currencyconverter.data.repository.CurrenciesRepository
import io.reactivex.Flowable
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FetchCurrenciesUseCase @Inject constructor(
    private val currenciesRepository: CurrenciesRepository,
    private val schedulerProvider: BaseSchedulerProvider
) {

    fun getAllCurrencies(baseCurrency: String): Flowable<Currencies> {
        return currenciesRepository.getCurrencies(baseCurrency).subscribeOn(schedulerProvider.io())
            .onBackpressureBuffer()
            .observeOn(schedulerProvider.ui()).repeatWhen {
                Flowable.timer(2, TimeUnit.SECONDS).repeat()
            }
    }
}