package com.cyborg.currencyconverter.presentation.common

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import com.cyborg.currencyconverter.data.entity.CurrenciesEntity
import com.cyborg.currencyconverter.data.model.Currencies
import io.reactivex.Flowable
import org.reactivestreams.Publisher

fun <T> Publisher<T>.toLiveData() = LiveDataReactiveStreams.fromPublisher(this) as LiveData<T>

fun <T> Flowable<T>.toState(): Flowable<State<T>> {
    return compose { item ->
        item
            .map { State.success(it) }
            .startWith(State.loading())
            .onErrorReturn { e ->
                State.error(
                    e.message ?: "Unknown Error",
                    e
                )
            }
    }
}

fun defaultErrorHandler(tag: String): (Throwable) -> Unit =
    { e -> Log.e(tag, e.message.toString()) }

fun Currencies.toCurrenciesEntity(): CurrenciesEntity =
    CurrenciesEntity(base = this.base, date = this.date, rates = this.rates)

fun getConvertedCurrenciesRates(
    currencyRates: MutableMap<String, Double>,
    amountToBeConverted: Double,
    baseCurrencyKey: String
): Map<String, Double> {

    val convertedCurrencies: MutableMap<String, Double> = HashMap()

    for ((key, value) in currencyRates) {
        val baseCurrencyValue = currencyRates[baseCurrencyKey]!!
        if (key == baseCurrencyKey) {
            convertedCurrencies[key] = amountToBeConverted
        } else {
            convertedCurrencies[key] =
                convertCurrency(baseCurrencyValue, value, amountToBeConverted)
        }
    }

    return convertedCurrencies
}

fun convertCurrency(
    baseCurrencyValue: Double,
    targetCurrencyValue: Double,
    amountToBeConverted: Double
): Double {
    return getExchangeRate(baseCurrencyValue, targetCurrencyValue) * amountToBeConverted
}

fun getExchangeRate(
    baseCurrencyValue: Double,
    targetCurrencyValue: Double
): Double {
    return targetCurrencyValue / baseCurrencyValue
}