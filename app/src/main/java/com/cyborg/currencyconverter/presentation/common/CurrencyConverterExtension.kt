package com.cyborg.currencyconverter.presentation.common

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import io.reactivex.Flowable
import org.reactivestreams.Publisher

fun <T> Publisher<T>.toLiveData() = LiveDataReactiveStreams.fromPublisher(this) as LiveData<T>

fun <T> Flowable<T>.toState(tag: String): Flowable<State<T>> {
    return compose { item ->
        item
            .map { State.success(it) }
            .startWith(State.loading())
            .onErrorReturn { e ->
                Log.d(tag, e.toString()); State.error(
                e.message ?: "Unknown Error",
                e
            )
            }
    }
}