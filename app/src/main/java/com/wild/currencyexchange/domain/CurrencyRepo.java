package com.wild.currencyexchange.domain;

// Created by Wild on 24.10.2016.

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class CurrencyRepo {

    private static final String TAG = CurrencyRepo.class.getSimpleName();
    private Map<Currency, Float> data;

    public CurrencyRepo() {
        data = new HashMap<>();
    }

    public void set(Currency currency, float value) {
        Log.d(TAG, String.format("set value %s = %.4f", currency, value));
        data.put(currency, value);
    }

    public float get(Currency currency) {
        if (data.containsKey(currency))
            return data.get(currency);

        return 0.0f;
    }

}
