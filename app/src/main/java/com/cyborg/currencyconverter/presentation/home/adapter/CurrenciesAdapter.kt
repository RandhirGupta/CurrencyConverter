package com.cyborg.currencyconverter.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cyborg.currencyconverter.databinding.CurrenciesItemLayoutBinding
import java.util.*
import kotlin.collections.ArrayList


class CurrenciesAdapter(private var currencyRates: Map<String, Double>) :
    RecyclerView.Adapter<CurrenciesAdapter.CurrenciesViewHolder>() {

    private var mCurrenciesList: List<Map.Entry<String, Double>> =
        ArrayList<Map.Entry<String, Double>>(currencyRates.entries)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrenciesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val dataBinding = CurrenciesItemLayoutBinding.inflate(layoutInflater, parent, false)
        return CurrenciesViewHolder(
            dataBinding
        )
    }

    override fun onBindViewHolder(holder: CurrenciesViewHolder, position: Int) {
        val currency = mCurrenciesList[position]

        holder.binding.currencyRatesEt.setText(currency.value.toString())
        holder.binding.currencyTitleTv.text = currency.key
        holder.binding.currencyDescTv.text = currency.key

        val drawable = holder.itemView.context.resources.getIdentifier(
            "flag_" + currency.key.toLowerCase(Locale.ENGLISH),
            "drawable", holder.itemView.context.packageName
        )

        holder.binding.countryFlagIv.setImageResource(drawable)
    }

    override fun getItemCount(): Int = mCurrenciesList.size

    class CurrenciesViewHolder(var binding: CurrenciesItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}
