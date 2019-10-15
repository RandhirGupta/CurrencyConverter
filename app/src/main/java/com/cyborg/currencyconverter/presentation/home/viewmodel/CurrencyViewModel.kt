package com.cyborg.currencyconverter.presentation.home.viewmodel

import androidx.lifecycle.LiveData
import com.cyborg.currencyconverter.data.entity.CurrenciesEntity
import com.cyborg.currencyconverter.data.usecase.FetchCurrenciesUseCase
import com.cyborg.currencyconverter.presentation.base.BaseViewModel
import com.cyborg.currencyconverter.presentation.common.SingleLiveEvent
import com.cyborg.currencyconverter.presentation.common.defaultErrorHandler
import com.cyborg.currencyconverter.presentation.common.toLiveData
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class CurrencyViewModel @Inject constructor(private val fetchCurrenciesUseCase: FetchCurrenciesUseCase) :
    BaseViewModel() {

    val currencies: LiveData<CurrenciesEntity> by lazy {
        fetchCurrenciesUseCase.getCurrenciesFromLocal("EUR")
            .toLiveData()
    }

    val singleEventCurrencies: SingleLiveEvent<CurrenciesEntity> = SingleLiveEvent()

    fun getSingleLiveEventCurrencies() {
        fetchCurrenciesUseCase.getCurrenciesFromLocal("EUR")
            .doOnNext {
                singleEventCurrencies.postValue(it)
            }.doOnError { e ->
                singleEventCurrencies.postValue(null)
            }
            .subscribeBy(onError = defaultErrorHandler("Currency View Model"))
            .addTo(compositeDisposable)

    }
}

