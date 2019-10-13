package com.cyborg.currencyconverter.presentation.home.viewmodel

import androidx.lifecycle.LiveData
import com.cyborg.currencyconverter.data.entity.CurrenciesEntity
import com.cyborg.currencyconverter.data.usecase.FetchCurrenciesUseCase
import com.cyborg.currencyconverter.presentation.base.BaseViewModel
import com.cyborg.currencyconverter.presentation.common.toLiveData
import javax.inject.Inject

class CurrencyViewModel @Inject constructor(private val fetchCurrenciesUseCase: FetchCurrenciesUseCase) :
    BaseViewModel() {

    val currencies: LiveData<CurrenciesEntity> by lazy {
        fetchCurrenciesUseCase.getCurrenciesFromLocal("EUR")
            .toLiveData()
    }
}