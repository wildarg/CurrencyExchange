package com.wild.currencyexchange.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

// Created by Wild on 24.10.2016.
public class CurrencyRepoTest {
    private static final float MARGIN_OF_ERROR = 0.00001f;

    private CurrencyRepo repo;

    @Before
    public void setUp() throws Exception {
        repo = new CurrencyRepo();
        repo.set(Currency.RUB, 100);
    }

    @Test
    public void testGetExistingCurrency() throws Exception {
        float expected = 100.0f;
        assertEquals(expected, repo.get(Currency.RUB), MARGIN_OF_ERROR);
    }

    @Test
    public void testGetNonExistingCurrency() throws Exception {
        float expected = 0.0f;
        assertEquals(expected, repo.get(Currency.EUR), MARGIN_OF_ERROR);
    }

    @Test
    public void testPutAnotherCurrency() {
        float rubExpected = 100.0f;
        float usdExpected = 200.4567f;
        repo.set(Currency.USD, usdExpected);

        assertEquals(usdExpected, repo.get(Currency.USD), MARGIN_OF_ERROR);
        assertEquals(rubExpected, repo.get(Currency.RUB), MARGIN_OF_ERROR);
    }

    @Test
    public void testPutSameCurrency() {
        float rubExpected = 345.6789f;
        repo.set(Currency.RUB, rubExpected);

        assertEquals(rubExpected, repo.get(Currency.RUB), MARGIN_OF_ERROR);
    }

}