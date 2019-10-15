package com.cyborg.currencyconverter.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.cyborg.currencyconverter.data.entity.CurrenciesEntity
import com.cyborg.currencyconverter.data.usecase.FetchCurrenciesUseCase
import com.cyborg.currencyconverter.presentation.home.viewmodel.CurrencyViewModel
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
class CurrencyViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var fetchCurrenciesUseCase: FetchCurrenciesUseCase

    @Mock
    private lateinit var currencyViewModel: CurrencyViewModel

    @Before
    fun setUp() {
        currencyViewModel = CurrencyViewModel(fetchCurrenciesUseCase)
    }

    @Test
    fun getCurrenciesFromLocal() {
        val currencyEntity = CurrenciesEntity(base = "EUR", rates = hashMapOf(), date = toString())
        val observer: Observer<CurrenciesEntity> = mock()

        whenever(fetchCurrenciesUseCase.getCurrenciesFromLocal("EUR")).doReturn(
            Flowable.just(
                currencyEntity
            )
        )

        currencyViewModel.currencies.observeForever(observer)

        verify(fetchCurrenciesUseCase).getCurrenciesFromLocal("EUR")
        verify(observer).onChanged(currencyEntity)
    }

    @Test
    fun getSingleEventCurrenciesFromLocal() {

        val currencyEntity = CurrenciesEntity(base = "EUR", rates = hashMapOf(), date = toString())
        val observer: Observer<CurrenciesEntity> = mock()

        whenever(fetchCurrenciesUseCase.getCurrenciesFromLocal("EUR")).doReturn(
            Flowable.just(
                currencyEntity
            )
        )

        currencyViewModel.getSingleLiveEventCurrencies()
        currencyViewModel.singleEventCurrencies.observeForever(observer)

        verify(fetchCurrenciesUseCase).getCurrenciesFromLocal("EUR")
        verify(observer).onChanged(currencyEntity)
    }
}