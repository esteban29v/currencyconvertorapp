package com.example.currencyapp.strategy;

import org.springframework.stereotype.Component;

@Component
public class ClpConverter implements CurrencyConverterInterface {

	public static final String CURRENCY_CODE = "CLP";
	public static final double USD_RATE = 0.0011;

    @Override
    public String getCurrencyCode() {
        return CURRENCY_CODE;
    }

    @Override
    public double convert(double value) {
        return value * USD_RATE;
    }
}