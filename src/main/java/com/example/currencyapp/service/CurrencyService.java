package com.example.currencyapp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import com.example.currencyapp.strategy.CurrencyConverterInterface;


@Service
public class CurrencyService implements InitializingBean {

    @Value("${currency.service.maxattempts}")
    private int MAX_ATTEMPTS = 3;

    @Value("${currency.service.failprobability}")
    private double FAILURE_PROBABILITY = 0.9;
    private static final Random RANDOM = new Random();

    private final List<CurrencyConverterInterface> converters;
    private final Map<String, CurrencyConverterInterface> converterMap = new HashMap<>();

    public CurrencyService(List<CurrencyConverterInterface> converters) {
        this.converters = converters;
    }

    @Override
    public void afterPropertiesSet() {
        for (CurrencyConverterInterface converter : converters) {
            converterMap.put(converter.getCurrencyCode(), converter);
        }
    }

    @Retryable(
        value = { RuntimeException.class },
        maxAttemptsExpression = "${currency.service.maxattempts}",
        backoff = @Backoff(delay = 1000)
    )
    public Double convertWithRetry(String currencyCode, double value) {
        double chance = RANDOM.nextDouble();
        if (chance < FAILURE_PROBABILITY) {
            throw new RuntimeException("Se alcanzó el número máximo (" + MAX_ATTEMPTS + ") de intentos fallidos ");
        }

        CurrencyConverterInterface converter = converterMap.get(currencyCode);
        if (converter == null) {
            throw new IllegalArgumentException("Moneda no soportada: " + currencyCode);
        }

        return converter.convert(value);
    }

    @Recover
    public Double recover(RuntimeException e, String currencyCode, double value) {
        throw new RuntimeException("Se alcanzó el número máximo de reintentos fallidos", e);
    }
}