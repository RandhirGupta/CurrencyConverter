package com.cyborg.currencyconverter

import com.cyborg.currencyconverter.di.ApplicationModule
import com.cyborg.currencyconverter.di.DatabaseModule
import com.cyborg.currencyconverter.di.NetworkModule
import com.cyborg.currencyconverter.di.UseCaseModule
import com.cyborg.currencyconverter.di.component.DaggerApplicationComponent
import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class CurrencyConverterApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder()
            .application(this)
            .applicationModule(ApplicationModule(applicationContext))
            .databaseModule(DatabaseModule())
            .networkModule(NetworkModule())
            .useCaseModule(UseCaseModule())
            .build()
    }
}