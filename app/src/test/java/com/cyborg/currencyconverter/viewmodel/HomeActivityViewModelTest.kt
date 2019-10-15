package com.cyborg.currencyconverter.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.cyborg.currencyconverter.data.entity.CurrenciesEntity
import com.cyborg.currencyconverter.data.model.Currencies
import com.cyborg.currencyconverter.data.usecase.FetchCurrenciesUseCase
import com.cyborg.currencyconverter.presentation.common.State
import com.cyborg.currencyconverter.presentation.home.viewmodel.HomeActivityViewModel
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeActivityViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var fetchCurrenciesUseCase: FetchCurrenciesUseCase

    @Mock
    private lateinit var homeActivityViewModel: HomeActivityViewModel

    @Before
    fun setUp() {
        homeActivityViewModel = HomeActivityViewModel(fetchCurrenciesUseCase)
    }

    @Test
    fun getCurrenciesFromNetworkThenStateSuccess() {
        val currencies = Currencies(base = "EUR", rates = hashMapOf(), date = toString())
        val observer: Observer<State<Currencies>> = mock()

        whenever(fetchCurrenciesUseCase.getCurrenciesFromNetwork("EUR")).doReturn(
            Flowable.just(
                currencies
            )
        )

        homeActivityViewModel.currencies.observeForever(observer)

        verify(fetchCurrenciesUseCase).getCurrenciesFromNetwork("EUR")
        verify(observer).onChanged(State.success(currencies))
    }

    @Test
    fun getCurrenciesFromNetworkThenStateError() {
        val error = Throwable("Unknown Error")
        val observer: Observer<State<Currencies>> = mock()

        whenever(fetchCurrenciesUseCase.getCurrenciesFromNetwork("EUR")).doReturn(
            Flowable.error(error)
        )

        homeActivityViewModel.currencies.observeForever(observer)

        verify(fetchCurrenciesUseCase).getCurrenciesFromNetwork("EUR")
        verify(observer).onChanged(State.error(error.localizedMessage, error))
    }
}