package com.cyborg.currencyconverter.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cyborg.currencyconverter.data.entity.CurrenciesEntity
import com.cyborg.currencyconverter.data.local.CurrenciesDatabase.Companion.DATABASE_NAME
import com.cyborg.currencyconverter.presentation.common.MapTypeConverter
import com.cyborg.currencyconverter.presentation.common.SingletonHolder

@Database(entities = [CurrenciesEntity::class], version = 1)
@TypeConverters(MapTypeConverter::class)
abstract class CurrenciesDatabase : RoomDatabase() {

    abstract fun currenciesDao(): CurrenciesDao

    companion object : SingletonHolder<CurrenciesDatabase, Context>({
        Room.databaseBuilder(it.applicationContext, CurrenciesDatabase::class.java, DATABASE_NAME)
            .build()
    }) {
        internal const val DATABASE_NAME = "currencies.db"
        internal const val TABLE_CURRENCY = "currency"
    }
}