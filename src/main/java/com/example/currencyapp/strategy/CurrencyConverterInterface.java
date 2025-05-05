package com.example.currencyapp.strategy;

public interface CurrencyConverterInterface {
    String getCurrencyCode();
    double convert(double value);
}