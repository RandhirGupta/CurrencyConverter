package com.cyborg.currencyconverter.presentation.home.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.cyborg.currencyconverter.R
import com.cyborg.currencyconverter.databinding.FragmentCurrencyLayoutBinding
import com.cyborg.currencyconverter.presentation.base.BaseFragment
import com.cyborg.currencyconverter.presentation.common.CurrencyState
import com.cyborg.currencyconverter.presentation.home.adapter.CurrenciesAdapter
import com.cyborg.currencyconverter.presentation.home.viewmodel.CurrencyViewModel
import javax.inject.Inject

class CurrencyFragment : BaseFragment() {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    private var mCurrencyViewModel: CurrencyViewModel? = null

    private lateinit var mCurrencyFragmentBinding: FragmentCurrencyLayoutBinding

    private lateinit var mCurrenciesAdapter: CurrenciesAdapter

    public var mCurrencyState: CurrencyState = CurrencyState.RATES
    private var isConverterAdapterLoaded: Boolean = false

    companion object {

        private const val BUNDLE_EXTRA_CURRENCY_STATE = "currency_state"

        fun getInstance(currencyState: CurrencyState): Fragment {
            val currencyFragment = CurrencyFragment()
            currencyFragment.arguments = getBundle(currencyState)
            return currencyFragment
        }

        private fun getBundle(currencyState: CurrencyState): Bundle {
            val bundle = Bundle()
            bundle.putSerializable(BUNDLE_EXTRA_CURRENCY_STATE, currencyState)
            return bundle
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mCurrencyState = arguments?.getSerializable(BUNDLE_EXTRA_CURRENCY_STATE) as CurrencyState
    }

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
        when (mCurrencyState) {
            CurrencyState.RATES -> getCurrencyObserver()
            CurrencyState.CURRENCY_CONVERSION -> getSingleEventCurrencyObserver()
        }
    }

    private fun initAdapter() {
        mCurrenciesAdapter = CurrenciesAdapter(mCurrencyState)
        mCurrencyFragmentBinding.currenciesRv.adapter = mCurrenciesAdapter
    }

    private fun getSingleEventCurrencyObserver() {

        mCurrencyViewModel?.getSingleLiveEventCurrencies()

        mCurrencyViewModel?.singleEventCurrencies?.observe(this, Observer {
            if (!isConverterAdapterLoaded) {
                isConverterAdapterLoaded = true
                mCurrenciesAdapter.setCurrencyBaseRates(it.rates)
                mCurrenciesAdapter.setCurrencyRates(it.rates)
            }
        })
    }

    private fun getCurrencyObserver() {
        mCurrencyViewModel?.currencies?.observe(this, Observer {
            mCurrenciesAdapter.setCurrencyBaseRates(it.rates)
            mCurrenciesAdapter.setCurrencyRates(it.rates)
        })
    }
}