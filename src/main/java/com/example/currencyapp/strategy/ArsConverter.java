package com.example.currencyapp.strategy;

import org.springframework.stereotype.Component;

@Component
public class ArsConverter implements CurrencyConverterInterface {

	public static final String CURRENCY_CODE = "ARS";
	public static final double USD_RATE = 0.00085;

    @Override
    public String getCurrencyCode() {
        return CURRENCY_CODE;
    }

    @Override
    public double convert(double value) {
        return value * USD_RATE;
    }
}