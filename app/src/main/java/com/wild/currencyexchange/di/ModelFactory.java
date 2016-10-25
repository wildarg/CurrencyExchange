package com.wild.currencyexchange.di;

// Created by Wild on 25.10.2016.

import com.wild.currencyexchange.domain.Currency;
import com.wild.currencyexchange.domain.CurrencyExchanger;

public class ModelFactory {

    private static CurrencyExchanger exchanger;

    synchronized public static CurrencyExchanger getCurrencyExchanger() {
        if (exchanger == null)
            exchanger = new CurrencyExchanger(Currency.EUR);
        return exchanger;
    }

    private ModelFactory() {}
}
