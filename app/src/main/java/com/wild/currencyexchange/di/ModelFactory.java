package com.wild.currencyexchange.di;

// Created by Wild on 25.10.2016.

import com.wild.currencyexchange.domain.Currency;
import com.wild.currencyexchange.domain.CurrencyExchanger;
import com.wild.currencyexchange.domain.CurrencyRepo;

public class ModelFactory {

    private static CurrencyExchanger exchanger;
    private static CurrencyRepo repo;

    synchronized public static CurrencyExchanger getCurrencyExchanger() {
        if (exchanger == null)
            exchanger = new CurrencyExchanger(Currency.EUR);
        return exchanger;
    }

    synchronized public static CurrencyRepo getCurrencyRepo() {
        if (repo == null)
            repo = new CurrencyRepo();
        return repo;
    }

    private ModelFactory() {}
}
