package com.example.currencyapp.model;

public enum Currencies {
    COP(0.00024),
    ARS(0.00085),
    EUR(1.13);

    private final double toUSD;

    Currencies(double toUSD) {
        this.toUSD = toUSD;
    }

    public double convert(Double value) {
        return value * toUSD;
    }
}
