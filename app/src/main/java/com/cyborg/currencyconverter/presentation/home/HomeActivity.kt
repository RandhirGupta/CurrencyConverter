package com.cyborg.currencyconverter.presentation.home

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
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

    private lateinit var mHomeActivityBinding: ActivityHomeBinding
    lateinit var mCurrencyViewPagerAdapter: CurrencyViewPagerAdapter

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
                is State.Loading -> showLoadingView()
                is State.Error -> showErrorView()
                is State.Success -> showSuccessView()
            }
        })
    }

    private fun initViewPager() {
        mCurrencyViewPagerAdapter = CurrencyViewPagerAdapter(supportFragmentManager)
        mHomeActivityBinding.currencyViewPager.adapter = mCurrencyViewPagerAdapter
        mHomeActivityBinding.currencyTabLayout.setupWithViewPager(mHomeActivityBinding.currencyViewPager)
    }

    private fun startRotatingImage() {
        val startRotateAnimation = AnimationUtils.loadAnimation(this, R.anim.linear_interpolator)
        mHomeActivityBinding.loadingViewPb.startAnimation(startRotateAnimation)
    }

    private fun showLoadingView() {
        startRotatingImage()
        mHomeActivityBinding.loadingView.visibility = View.VISIBLE
        mHomeActivityBinding.errorView.visibility = View.GONE
        mHomeActivityBinding.currencyTabView.visibility = View.GONE
    }

    private fun showErrorView() {
        mHomeActivityBinding.loadingView.visibility = View.GONE
        mHomeActivityBinding.errorView.visibility = View.VISIBLE
        mHomeActivityBinding.currencyTabView.visibility = View.GONE

        mHomeActivityBinding.retryButton.setOnClickListener {
            observeCurrencies()
        }
    }

    private fun showSuccessView() {
        mHomeActivityBinding.loadingView.visibility = View.GONE
        mHomeActivityBinding.errorView.visibility = View.GONE
        mHomeActivityBinding.currencyTabView.visibility = View.VISIBLE
    }
}
