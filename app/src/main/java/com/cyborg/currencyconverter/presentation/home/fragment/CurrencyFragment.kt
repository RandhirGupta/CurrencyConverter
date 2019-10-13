package com.cyborg.currencyconverter.presentation.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.cyborg.currencyconverter.R
import com.cyborg.currencyconverter.databinding.FragmentCurrencyLayoutBinding
import com.cyborg.currencyconverter.presentation.base.BaseFragment
import com.cyborg.currencyconverter.presentation.home.adapter.CurrenciesAdapter
import com.cyborg.currencyconverter.presentation.home.viewmodel.CurrencyViewModel
import javax.inject.Inject

class CurrencyFragment : BaseFragment() {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    private var mCurrencyViewModel: CurrencyViewModel? = null

    private lateinit var mCurrencyFragmentBinding: FragmentCurrencyLayoutBinding

    private lateinit var mCurrenciesAdapter: CurrenciesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mCurrencyViewModel = activity?.run {
            ViewModelProviders.of(this, mViewModelFactory).get(CurrencyViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mCurrencyFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_currency_layout, container, false)

        mCurrencyFragmentBinding.lifecycleOwner = this

        return mCurrencyFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        getCurrenciesFromLocal()
    }

    private fun getCurrenciesFromLocal() {
        mCurrencyViewModel?.currencies?.observe(this, Observer {
            mCurrenciesAdapter.setCurrencyRates(it.rates)
        })
    }

    private fun initAdapter() {
        mCurrenciesAdapter = CurrenciesAdapter()
        mCurrencyFragmentBinding.currenciesRv.adapter = mCurrenciesAdapter
    }
}