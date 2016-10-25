package com.wild.currencyexchange.utils;

// Created by Wild on 25.10.2016.

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

public class BroadcastUtils {

    public static final String EVENT_RATES_CHANGED = "com.wild.currencyexchange.RatesChanged";

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

}
