package com.cyborg.currencyconverter.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.cyborg.currencyconverter.data.entity.CurrenciesEntity
import io.reactivex.Flowable

@Dao
interface CurrenciesDao {

    @Insert
    fun insertCurrencies(currenciesEntity: CurrenciesEntity)

    @Query("SELECT * FROM " + CurrenciesDatabase.TABLE_CURRENCY + " WHERE base = :baseCurrency")
    fun getCurrencies(baseCurrency: String): Flowable<CurrenciesEntity>
}