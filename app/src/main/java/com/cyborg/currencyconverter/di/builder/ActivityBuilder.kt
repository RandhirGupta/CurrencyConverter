package com.cyborg.currencyconverter.di.builder

import com.cyborg.currencyconverter.di.module.MainModule
import com.cyborg.currencyconverter.presentation.home.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainModule::class])
    fun contributeHomeActivity(): HomeActivity
}