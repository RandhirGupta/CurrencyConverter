package com.cyborg.currencyconverter.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.cyborg.currencyconverter.data.local.CurrenciesDatabase
import com.cyborg.currencyconverter.presentation.common.MapTypeConverter
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = CurrenciesDatabase.TABLE_CURRENCY)
data class CurrenciesEntity(
    @PrimaryKey
    val base: String,
    val date: String,

    @TypeConverters(MapTypeConverter::class)
    val rates: HashMap<String, Double>
) : Parcelable