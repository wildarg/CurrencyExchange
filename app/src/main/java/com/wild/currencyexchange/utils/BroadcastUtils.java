package com.wild.currencyexchange.utils;

// Created by Wild on 25.10.2016.

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import com.wild.currencyexchange.domain.Currency;

public class BroadcastUtils {

    public static final String EVENT_RATES_CHANGED = "com.wild.currencyexchange.RatesChanged";
    public static final String EVENT_CURRENCY_VALUE_CHANGED = "com.wild.currencyexchange.ValueChanged";
    public static final String EVENT_CURRENCY_CHANGED = "com.wild.currencyexchange.CurrencyChanged";
    public static final String CURRENCY_EXTRA = "currency_extra";

    public static void notifyRatesChanged(Context context) {
        LocalBroadcastManager.getInstance(context)
                .sendBroadcast(new Intent(EVENT_RATES_CHANGED));
    }

    public static void register(Context context, BroadcastReceiver receiver, String event) {
        LocalBroadcastManager.getInstance(context)
                .registerReceiver(receiver, new IntentFilter(event));
    }

    public static void unregister(Context context, BroadcastReceiver receiver) {
        if (receiver != null)
            LocalBroadcastManager.getInstance(context)
                .unregisterReceiver(receiver);
    }

    public static void notifyValueChanged(Context context, Currency currency) {
        Intent i = new Intent(EVENT_CURRENCY_VALUE_CHANGED);
        i.putExtra(CURRENCY_EXTRA, currency);
        LocalBroadcastManager.getInstance(context)
                .sendBroadcast(i);
    }

    public static void notifyCurrencyChanged(Context context, Currency currency) {
        Intent i = new Intent(EVENT_CURRENCY_CHANGED);
        i.putExtra(CURRENCY_EXTRA, currency);
        LocalBroadcastManager.getInstance(context)
                .sendBroadcast(i);
    }

}
