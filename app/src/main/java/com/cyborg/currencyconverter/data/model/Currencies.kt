package com.cyborg.currencyconverter.data.model

data class Currencies(
    val base: String,
    val date: String,
    val rates: HashMap<String, Double>
)