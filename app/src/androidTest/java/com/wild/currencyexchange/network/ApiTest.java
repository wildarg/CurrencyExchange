package com.wild.currencyexchange.network;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import retrofit2.Response;

import static org.junit.Assert.*;

// Created by Wild on 24.10.2016.
public class ApiTest {

    Api api;

    @Before
    public void setUp() {
        api = Api.Factory.create();
    }

    @Test
    public void testCreateApi() {
        assertNotNull(api);
    }

    @Test
    public void testNotNullResponse() throws Exception {
        Response<RateDataEntity> response = api.getRates().execute();
        assertNotNull(response);
        assertNotNull(response.body());
    }

    @Test
    public void testGetRates() throws Exception {
        RateDataEntity r = api.getRates().execute().body();
        List<RateDataEntity.RateCube> rates = r.cube.cube.rates;
        assertNotNull(rates);
        assertTrue(rates.size() > 0);
        assertTrue(rates.get(0) != null && !rates.get(0).equals(""));
        assertTrue(rates.get(0).rate > 0.0f);
    }

}