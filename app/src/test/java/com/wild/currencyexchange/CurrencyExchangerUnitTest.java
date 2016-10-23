package com.wild.currencyexchange;

import com.wild.currencyexchange.domain.Currency;
import com.wild.currencyexchange.domain.CurrencyExchanger;
import com.wild.currencyexchange.domain.RateNotFoundException;

import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.*;

// Created by Wild on 24.10.2016.

public class CurrencyExchangerUnitTest {

    private static final float MARGIN_OF_ERROR = 0.00001f;
    private CurrencyExchanger exchanger;

    @Before
    public void setUp() {
        exchanger = new CurrencyExchanger(Currency.EUR);
        exchanger.setRate(Currency.USD, 1.08f);
        exchanger.setRate(Currency.GBP, 0.89f);
    }

    @Test
    public void testBaseCurrencyRate() throws RateNotFoundException {
        float expected = 1.0f;
        assertEquals(expected, exchanger.getRate(Currency.EUR), MARGIN_OF_ERROR);
    }

    @Test
    public void testGetCurrencyRate() throws RateNotFoundException {
        float gbpRateExpected = 0.89f;
        assertEquals(gbpRateExpected, exchanger.getRate(Currency.GBP), MARGIN_OF_ERROR);
    }

    @Test
    public void testRateNotFoundException() {
        try {
            exchanger.getRate(Currency.RUB);
            fail();
        } catch (RateNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetCrossRate() throws RateNotFoundException {
        float expectedCrossRate = 1.08f / 0.89f;
        float crossRate = exchanger.getCrossRate(Currency.GBP, Currency.USD);
        assertEquals(expectedCrossRate, crossRate, MARGIN_OF_ERROR);
        System.out.println(String.format(Locale.getDefault(),
                "%.2f %s equals %.2f %s",
                100.0f, Currency.GBP,
                100.0f*crossRate, Currency.USD));
    }

}
