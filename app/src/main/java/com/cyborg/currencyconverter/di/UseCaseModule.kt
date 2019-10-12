package com.cyborg.currencyconverter.di

import com.cyborg.currencyconverter.data.executor.BaseSchedulerProvider
import com.cyborg.currencyconverter.data.executor.SchedulerProvider
import com.cyborg.currencyconverter.data.local.LocalSource
import com.cyborg.currencyconverter.data.repository.CurrenciesRepository
import com.cyborg.currencyconverter.data.usecase.FetchCurrenciesUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Singleton
    @Provides
    fun provideSchedulerProvider(): BaseSchedulerProvider {
        return SchedulerProvider()
    }

    @Singleton
    @Provides
    fun provideFetchCurrenciesUseCase(
        currenciesRepository: CurrenciesRepository,
        schedulerProvider: BaseSchedulerProvider,
        localSource: LocalSource
    ): FetchCurrenciesUseCase {
        return FetchCurrenciesUseCase(currenciesRepository, schedulerProvider, localSource)
    }
}