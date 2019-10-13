package com.cyborg.currencyconverter.presentation.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.cyborg.currencyconverter.presentation.home.fragment.CurrencyFragment

class CurrencyViewPagerAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager) {

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return CurrencyFragment()
    }
}