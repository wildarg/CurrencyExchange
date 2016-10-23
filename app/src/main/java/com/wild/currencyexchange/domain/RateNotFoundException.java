package com.wild.currencyexchange.domain;

// Created by Wild on 24.10.2016.

public class RateNotFoundException extends Exception {

    private final Currency currency;

    public RateNotFoundException(Currency currency, String msg) {
        super(msg);
        this.currency = currency;
    }

    public Currency getCurrency() {
        return currency;
    }
}
