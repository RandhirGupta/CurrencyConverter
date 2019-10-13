package com.cyborg.currencyconverter.data.local

import android.database.sqlite.SQLiteConstraintException
import androidx.room.*
import com.cyborg.currencyconverter.data.entity.CurrenciesEntity
import io.reactivex.Flowable

@Dao
interface CurrenciesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrencies(currenciesEntity: CurrenciesEntity)

    @Query("SELECT * FROM " + CurrenciesDatabase.TABLE_CURRENCY + " WHERE base = :baseCurrency")
    fun getCurrencies(baseCurrency: String): Flowable<CurrenciesEntity>
}