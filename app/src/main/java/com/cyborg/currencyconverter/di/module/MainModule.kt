package com.cyborg.currencyconverter.di.module

import androidx.lifecycle.ViewModel
import com.cyborg.currencyconverter.di.ViewModelKey
import com.cyborg.currencyconverter.presentation.home.HomeActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeActivityViewModel::class)
    fun bindHomeActivityViewModel(homeActivityViewModel: HomeActivityViewModel): ViewModel
}