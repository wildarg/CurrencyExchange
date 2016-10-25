package com.wild.currencyexchange.domain;

// Created by Wild on 24.10.2016.

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class CurrencyExchanger {

    private static final String TAG = CurrencyExchanger.class.getSimpleName();
    private final Map<Currency, Float> rates;

    public CurrencyExchanger(Currency baseCurrency) {
        rates = new HashMap<>();
        setRate(baseCurrency, 1);
    }

    public void setRate(Currency currency, float rate) {
        Log.d(TAG, String.format("set rate %s = %.4f", currency, rate));
        rates.put(currency, rate);
    }

    public float getCrossRate(Currency from, Currency to) throws RateNotFoundException {
        return getRate(to) / getRate(from);
    }

    public float getRate(Currency currency) throws RateNotFoundException {
        if (!rates.containsKey(currency))
            throw new RateNotFoundException(currency, "Rate not found for " + currency.name());
        return rates.get(currency);
    }

}
