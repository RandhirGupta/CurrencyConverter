package com.cyborg.currencyconverter.presentation.home.fragment

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.cyborg.currencyconverter.presentation.base.BaseFragment
import com.cyborg.currencyconverter.presentation.home.viewmodel.CurrencyViewModel
import javax.inject.Inject

class CurrencyFragment : BaseFragment() {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    private var mCurrencyViewModel: CurrencyViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mCurrencyViewModel = activity?.run {
            ViewModelProviders.of(this, mViewModelFactory).get(CurrencyViewModel::class.java)
        }
    }
}