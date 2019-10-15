package com.cyborg.currencyconverter.presentation.home.viewmodel

import androidx.lifecycle.LiveData
import com.cyborg.currencyconverter.data.model.Currencies
import com.cyborg.currencyconverter.data.usecase.FetchCurrenciesUseCase
import com.cyborg.currencyconverter.presentation.base.BaseViewModel
import com.cyborg.currencyconverter.presentation.common.State
import com.cyborg.currencyconverter.presentation.common.toLiveData
import com.cyborg.currencyconverter.presentation.common.toState
import javax.inject.Inject

class HomeActivityViewModel @Inject constructor(private val fetchCurrenciesUseCase: FetchCurrenciesUseCase) :
    BaseViewModel() {

    val currencies: LiveData<State<Currencies>> by lazy {
        fetchCurrenciesUseCase.getCurrenciesFromNetwork("EUR").toState()
            .toLiveData()
    }
}