package com.cyborg.currencyconverter.presentation.home.adapter

import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatEditText
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cyborg.currencyconverter.databinding.CurrenciesItemLayoutBinding
import com.cyborg.currencyconverter.presentation.common.CurrencyState
import com.cyborg.currencyconverter.presentation.common.getConvertedCurrenciesRates
import java.util.*
import kotlin.collections.ArrayList


class CurrenciesAdapter(private val currencyState: CurrencyState) :
    RecyclerView.Adapter<CurrenciesAdapter.CurrenciesViewHolder>() {

    private var mCurrenciesList: MutableList<String> = ArrayList()
    private lateinit var mCurrenciesRates: MutableMap<String, Double>

    fun setCurrencyBaseRates(currencyRates: Map<String, Double>) {
        mCurrenciesRates = currencyRates as MutableMap<String, Double>
        notifyDataSetChanged()
    }

    fun setAdapterData(currencyRates: Map<String, Double>) {
        mCurrenciesList = ArrayList(currencyRates.keys)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrenciesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val dataBinding = CurrenciesItemLayoutBinding.inflate(layoutInflater, parent, false)
        return CurrenciesViewHolder(
            dataBinding
        )
    }

    override fun onBindViewHolder(holder: CurrenciesViewHolder, position: Int) {
        val currencyKey = mCurrenciesList[position]
        val currencyValue = mCurrenciesRates[currencyKey]

        currencyValue?.let { holder.bindView(currencyKey, it, currencyState, position) }
    }

    override fun getItemCount(): Int = mCurrenciesList.size

    inner class CurrenciesViewHolder(private var binding: CurrenciesItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var textChangeWatcher: TextChangeWatcher? = null
        private lateinit var currencyKey: String

        fun bindView(
            currencyKey: String, currencyValue: Double,
            currencyState: CurrencyState,
            position: Int
        ) {

            this.currencyKey = currencyKey

            removeTextWatcher(binding.currencyRatesEt)

            when (currencyState) {
                CurrencyState.RATES -> binding.currencyRatesEt.isEnabled = false
                CurrencyState.CURRENCY_CONVERSION -> {
                    binding.currencyRatesEt.isEnabled = position == 0

                    itemView.setOnClickListener {
                        mCurrenciesList.remove(currencyKey)
                        mCurrenciesList.add(0, currencyKey)
                        notifyDataSetChanged()
                    }
                }
            }

            binding.currencyRatesEt.setText(currencyValue.toString())
            binding.currencyTitleTv.text = currencyKey

            binding.currencyDescTv.text = java.util.Currency.getInstance(currencyKey).displayName

            val drawable = itemView.context.resources.getIdentifier(
                "flag_" + currencyKey.toLowerCase(Locale.ENGLISH),
                "drawable", itemView.context.packageName
            )

            Glide.with(itemView.context).load(drawable).apply(RequestOptions.circleCropTransform())
                .into(binding.countryFlagIv)

            addTextWatcher(binding.currencyRatesEt)
        }

        private fun addTextWatcher(
            editText: AppCompatEditText
        ) {
            if (textChangeWatcher == null) {
                textChangeWatcher = TextChangeWatcher()
            }
            editText.addTextChangedListener(textChangeWatcher)
        }

        private fun removeTextWatcher(editText: AppCompatEditText) {
            if (textChangeWatcher != null) {
                editText.removeTextChangedListener(textChangeWatcher)
            }
        }

        inner class TextChangeWatcher :
            TextWatcher {

            private var mCountDownTimer: CountDownTimer? = null

            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                val enteredValue = p0.toString().toDoubleOrNull()
                enteredValue?.let { startCountDownToConvertCurrency(it) }
            }

            private fun startCountDownToConvertCurrency(enteredValue: Double) {

                mCountDownTimer?.cancel()

                mCountDownTimer = object : CountDownTimer(2000, 100) {
                    override fun onTick(millisUntilFinished: Long) = Unit

                    override fun onFinish() {
                        getConvertedCurrenciesRates(
                            mCurrenciesRates,
                            enteredValue,
                            currencyKey
                        ).let {
                            setCurrencyBaseRates(
                                it
                            )
                        }
                    }
                }
                mCountDownTimer?.start()
            }
        }
    }
}
