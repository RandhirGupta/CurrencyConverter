package com.cyborg.currencyconverter.di.builder

import com.cyborg.currencyconverter.di.module.MainModule
import com.cyborg.currencyconverter.presentation.home.fragment.CurrencyFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentBuilder {

    @ContributesAndroidInjector(modules = [MainModule::class])
    fun contributeCurrencyFragment(): CurrencyFragment
}