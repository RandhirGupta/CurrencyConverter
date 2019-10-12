package com.cyborg.currencyconverter.di

import com.cyborg.currencyconverter.data.local.LocalSource
import com.cyborg.currencyconverter.data.network.ApiService
import com.cyborg.currencyconverter.data.repository.CurrenciesRepository
import com.cyborg.currencyconverter.data.repository.CurrenciesRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideCurrenciesRepository(
        apiService: ApiService, localSource: LocalSource
    ): CurrenciesRepository {
        return CurrenciesRepositoryImpl(apiService, localSource)
    }
}