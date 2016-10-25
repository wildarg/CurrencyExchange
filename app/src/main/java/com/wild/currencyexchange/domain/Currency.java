package com.wild.currencyexchange.domain;

// Created by Wild on 24.10.2016.

public enum Currency {
    USD,
    EUR,
    GBP,
    RUB;

    public static Currency getFromName(String name) {
        for (Currency c: values())
            if (c.name().equals(name))
                return c;
        return null;
    }
}
