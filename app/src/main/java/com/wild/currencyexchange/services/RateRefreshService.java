package com.wild.currencyexchange.services;

// Created by Wild on 25.10.2016.

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.wild.currencyexchange.di.ModelFactory;
import com.wild.currencyexchange.domain.Currency;
import com.wild.currencyexchange.domain.CurrencyExchanger;
import com.wild.currencyexchange.network.Api;
import com.wild.currencyexchange.network.RateDataEntity;
import com.wild.currencyexchange.utils.BroadcastUtils;
import com.wild.currencyexchange.utils.Constants;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Response;

/**
 *
 * this binding service refreshes rates every 30 seconds
 *
 */

public class RateRefreshService extends Service {

    private static final String TAG = RateRefreshService.class.getSimpleName();
    private Timer timer;

    @Override
    public void onCreate() {
        super.onCreate();
        timer = new Timer();
        timer.schedule(new RateRefreshTask(), 1, Constants.REFRESH_TIME_IN_MILLIS);
    }

    @Override
    public void onDestroy() {
        if (timer != null)
            timer.cancel();
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }

    private class RateRefreshTask extends TimerTask {
        @Override
        public void run() {
            Log.d(TAG, "start refresh...");
            try {
                Api api = Api.Factory.create();
                Response<RateDataEntity> response = api.getRates().execute();

                if (response.isSuccessful())
                    refreshRates(response.body().cube.cube.rates);
                else
                    Log.e(TAG, "error on get rates, " + response.code() + ":" + response.message());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void refreshRates(List<RateDataEntity.RateCube> rates) {
            Log.d(TAG, "refresh rates");
            CurrencyExchanger ex = ModelFactory.getCurrencyExchanger();
            for (RateDataEntity.RateCube c: rates) {
                Currency currency = Currency.getFromName(c.currency);
                if (currency != null)
                    ex.setRate(currency, c.rate);
            }
            BroadcastUtils.notifyRatesChanged(getApplicationContext());
        }
    }
}
