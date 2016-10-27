package com.wild.currencyexchange.domain;

// Created by Wild on 24.10.2016.

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import com.wild.currencyexchange.R;

public enum Currency {
    USD(R.string.usd_char,
        R.drawable.usd_top,
        R.drawable.usd_bottom),
    EUR(R.string.eur_char,
        R.drawable.eur_top,
        R.drawable.eur_bottom),
    GBP(R.string.gbp_char,
        R.drawable.gbp_top,
        R.drawable.gbp_bottom),
    RUB(0,0,0);

    private final int currencyCharResId;
    private final int bkgTop;
    private final int bkgBottom;

    Currency(@StringRes int currencyCharResId,
             @DrawableRes int bkgTop,
             @DrawableRes int bkgBottom) {
        this.currencyCharResId = currencyCharResId;
        this.bkgTop = bkgTop;
        this.bkgBottom = bkgBottom;
    }

    public int getCurrencyCharResId() {
        return currencyCharResId;
    }

    public String marker(Context context) {
        return context.getString(currencyCharResId);
    }

    @DrawableRes
    public int getBkgTop() {
        return bkgTop;
    }

    @DrawableRes
    public int getBkgBottom() {
        return bkgBottom;
    }

    public static Currency getFromName(String name) {
        for (Currency c: values())
            if (c.name().equals(name))
                return c;
        return null;
    }
}
