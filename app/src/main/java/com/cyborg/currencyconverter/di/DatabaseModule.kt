package com.cyborg.currencyconverter.di

import android.content.Context
import com.cyborg.currencyconverter.data.executor.BaseSchedulerProvider
import com.cyborg.currencyconverter.data.local.CurrenciesDatabase
import com.cyborg.currencyconverter.data.local.LocalSource
import com.cyborg.currencyconverter.data.local.LocalSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DatabaseModule {

    @Provides
    fun provideDatabase(context: Context): CurrenciesDatabase {
        return CurrenciesDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideLocalSource(
        currenciesDatabase: CurrenciesDatabase,
        schedulerProvider: BaseSchedulerProvider
    ): LocalSource {
        return LocalSourceImpl(currenciesDatabase, schedulerProvider)
    }
}