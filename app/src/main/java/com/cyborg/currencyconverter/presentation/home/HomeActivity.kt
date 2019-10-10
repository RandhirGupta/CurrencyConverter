package com.cyborg.currencyconverter.presentation.home

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.cyborg.currencyconverter.R
import com.cyborg.currencyconverter.presentation.base.BaseActivity
import com.cyborg.currencyconverter.presentation.common.State
import javax.inject.Inject

class HomeActivity : BaseActivity() {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    private val mHomeActivityViewModel: HomeActivityViewModel by lazy {
        ViewModelProviders.of(this, mViewModelFactory).get(HomeActivityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        observeCurrencies()
    }

    private fun observeCurrencies() {
        mHomeActivityViewModel.currencies.observe(this, Observer {
            when (it) {
                is State.Loading -> Log.d("NNN", "loading")
                is State.Error -> Log.d("NNN", "error")
                is State.Success -> Log.d("NNN", it.data.base)
            }
        })
    }
}
