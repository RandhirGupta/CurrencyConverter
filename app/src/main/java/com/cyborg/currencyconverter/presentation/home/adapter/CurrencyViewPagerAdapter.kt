package com.cyborg.currencyconverter.presentation.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.cyborg.currencyconverter.presentation.common.CurrencyState
import com.cyborg.currencyconverter.presentation.home.fragment.CurrencyFragment

class CurrencyViewPagerAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager) {

    private val tabTitles = arrayOf("All Rates", "Converter")
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {

        var currencyState: CurrencyState = CurrencyState.RATES
        when (position) {
            0 -> currencyState = CurrencyState.RATES
            1 -> currencyState = CurrencyState.CURRENCY_CONVERSION
        }
        return CurrencyFragment.getInstance(currencyState)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitles[position]
    }
}