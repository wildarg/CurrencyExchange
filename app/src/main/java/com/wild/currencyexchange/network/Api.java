package com.wild.currencyexchange.network;

// Created by Wild on 24.10.2016.

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import retrofit2.http.GET;

public interface Api {

    @GET("stats/eurofxref/eurofxref-daily.xml")
    Call<RateDataEntity> getRates();

    class Factory {
        public static Api create() {

            return new Retrofit.Builder()
                    .baseUrl("http://www.ecb.int/")
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .client(provideHttpClient())
                    .build()
                    .create(Api.class);
        }

        private static OkHttpClient provideHttpClient() {
            return new OkHttpClient.Builder().build();
        }

    }

}
