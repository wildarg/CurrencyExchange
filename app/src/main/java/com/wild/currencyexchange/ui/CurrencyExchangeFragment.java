package com.wild.currencyexchange.ui;

// Created by Wild on 25.10.2016.

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.wild.currencyexchange.R;
import com.wild.currencyexchange.di.ModelFactory;
import com.wild.currencyexchange.domain.Currency;
import com.wild.currencyexchange.domain.CurrencyExchanger;
import com.wild.currencyexchange.domain.CurrencyRepo;
import com.wild.currencyexchange.domain.RateNotFoundException;
import com.wild.currencyexchange.utils.BroadcastUtils;
import com.wild.currencyexchange.utils.MoneyTextWatcher;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CurrencyExchangeFragment extends Fragment {

    private static final String ARG_CURRENCY = "arg_currency";
    private static final String TAG = CurrencyExchangeFragment.class.getSimpleName();

    private CurrencyRepo repo;
    private Currency currency;
    private CurrencyExchanger exchanger;

    @BindView(R.id.layout)
    View layout;
    @BindView(R.id.value_text)
    TextView valueText;
    @BindView(R.id.currency_marker)
    TextView currencyMarker;
    @BindView(R.id.rate_text)
    TextView rateText;

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            refreshView();
        }
    };

    public static CurrencyExchangeFragment newInstance(Currency currency) {
        Log.d(TAG, "create new instance for " + currency);
        CurrencyExchangeFragment f = new CurrencyExchangeFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_CURRENCY, currency);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repo = ModelFactory.getCurrencyRepo();
        exchanger = ModelFactory.getCurrencyExchanger();
        Bundle args = getArguments();
        currency = (Currency) args.getSerializable(ARG_CURRENCY);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.currency_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        currencyMarker.setText(currency.name());
        valueText.addTextChangedListener(new MoneyTextWatcher());
        layout.setBackgroundResource(currency.getBkgBottom());
        Log.d(TAG, "register receiver for " + currency);
        BroadcastUtils.register(getContext(), receiver, BroadcastUtils.EVENT_CURRENCY_CHANGED);
        BroadcastUtils.register(getContext(), receiver, BroadcastUtils.EVENT_CURRENCY_VALUE_CHANGED);
        BroadcastUtils.register(getContext(), receiver, BroadcastUtils.EVENT_RATES_CHANGED);
        refreshView();
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, "unregister receiver for " + currency);
        BroadcastUtils.unregister(getContext(), receiver);
        super.onDestroyView();
    }

    private void refreshView() {
        Currency from = ((MainActivity)getActivity()).getCurrentCurrency();
        try {
            float rate = 1.0f;
            if (from != currency)
                rate = exchanger.getCrossRate(currency, from);
            float value = repo.get(from) / rate;
            Log.d(TAG, currency.name() + " value: " + value);
            setValue(value);
            setRate(rate, from);
        } catch (RateNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setRate(float rate, Currency from) {
        if (currency == from)
            rateText.setText(null);
        else {
            rateText.setText(String.format(Locale.getDefault(),
                    "%s1 = %s%.4f",
                    currency.marker(getContext()),
                    from.marker(getContext()),
                    rate));
        }
    }

    private void setValue(float value) {
        valueText.setText(String.format(Locale.getDefault(), "%,.2f", value));
    }

}
