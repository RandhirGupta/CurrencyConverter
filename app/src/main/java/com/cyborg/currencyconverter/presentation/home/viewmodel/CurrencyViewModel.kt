package com.cyborg.currencyconverter.presentation.home.viewmodel

import androidx.lifecycle.LiveData
import com.cyborg.currencyconverter.data.entity.CurrenciesEntity
import com.cyborg.currencyconverter.data.usecase.FetchCurrenciesUseCase
import com.cyborg.currencyconverter.presentation.base.BaseViewModel
import com.cyborg.currencyconverter.presentation.common.State
import com.cyborg.currencyconverter.presentation.common.toLiveData
import com.cyborg.currencyconverter.presentation.common.toState
import javax.inject.Inject

class CurrencyViewModel @Inject constructor(private val fetchCurrenciesUseCase: FetchCurrenciesUseCase) :
    BaseViewModel() {

    val currencies: LiveData<State<CurrenciesEntity>> by lazy {
        fetchCurrenciesUseCase.getCurrenciesFromLocal("EUR").toState("Currency View Model")
            .toLiveData()
    }
}