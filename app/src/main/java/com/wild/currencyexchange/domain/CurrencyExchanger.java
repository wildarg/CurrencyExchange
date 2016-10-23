package com.wild.currencyexchange.domain;

// Created by Wild on 24.10.2016.

import java.util.HashMap;
import java.util.Map;

public class CurrencyExchanger {

    private final Map<Currency, Float> rates;

    public CurrencyExchanger(Currency baseCurrency) {
        rates = new HashMap<>();
        setRate(baseCurrency, 1);
    }

    public void setRate(Currency currency, float rate) {
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
