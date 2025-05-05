package com.example.currencyapp.strategy;

import org.springframework.stereotype.Component;

@Component
public class EurConverter implements CurrencyConverterInterface {

	public static final String CURRENCY_CODE = "EUR";
	public static final double USD_RATE = 1.13;

    @Override
    public String getCurrencyCode() {
        return CURRENCY_CODE;
    }

    @Override
    public double convert(double value) {
        return value * USD_RATE;
    }
}