package com.wild.currencyexchange.di;

// Created by Wild on 27.10.2016.

import com.wild.currencyexchange.network.Api;
import com.wild.currencyexchange.utils.Constants;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 *
 * simple injector
 *
 */

public class Injector {

    private Api api;

    public static Api getApi() {
        return new Retrofit.Builder()
                .baseUrl(Constants.ENTRY_POINT)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .client(provideHttpClient())
                .build()
                .create(Api.class);
    }

    private static OkHttpClient provideHttpClient() {
        return new OkHttpClient.Builder().build();
    }

}
