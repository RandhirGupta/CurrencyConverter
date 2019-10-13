package com.cyborg.currencyconverter.presentation.home

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.cyborg.currencyconverter.R
import com.cyborg.currencyconverter.databinding.ActivityHomeBinding
import com.cyborg.currencyconverter.presentation.base.BaseActivity
import com.cyborg.currencyconverter.presentation.common.State
import com.cyborg.currencyconverter.presentation.home.adapter.CurrencyViewPagerAdapter
import com.cyborg.currencyconverter.presentation.home.viewmodel.HomeActivityViewModel
import javax.inject.Inject

class HomeActivity : BaseActivity() {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    private val mHomeActivityViewModel: HomeActivityViewModel by lazy {
        ViewModelProviders.of(this, mViewModelFactory).get(HomeActivityViewModel::class.java)
    }

    lateinit var mHomeActivityBinding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mHomeActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        mHomeActivityBinding.lifecycleOwner = this

        observeCurrencies()
        initViewPager()
    }

    private fun observeCurrencies() {
        mHomeActivityViewModel.currencies.observe(this, Observer {
            when (it) {
                is State.Loading -> Log.d("NNN", "loading")
                is State.Error -> Log.d("NNN", "error")
                is State.Success -> Log.d("NNN", it.data.rates.toString())
            }
        })
    }

    private fun initViewPager() {
        val currencyViewPagerAdapter = CurrencyViewPagerAdapter(supportFragmentManager)
        mHomeActivityBinding.currencyViewPager.adapter = currencyViewPagerAdapter
    }
}
