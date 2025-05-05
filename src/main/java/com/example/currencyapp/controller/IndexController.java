package com.example.currencyapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.example.currencyapp.dto.CurrencyResponse;
import com.example.currencyapp.model.Currencies;
import com.example.currencyapp.service.CurrencyService;

import org.springframework.web.bind.annotation.*;
import java.util.Random;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


@RestController
@RequestMapping("/")
public class IndexController {

	private final CurrencyService currencyService;

    public IndexController(CurrencyService currencyService) {
		this.currencyService = currencyService;
	}
	

    @PostMapping("/currency")
    public ResponseEntity<CurrencyResponse> currencyMethod(
        @RequestParam @NotNull @Min(0) Double value,
        @RequestParam String currency) {

        try {
            Double result = currencyService.convertWithRetry(currency, value);
            return ResponseEntity.ok(
                new CurrencyResponse(true, "Conversión exitosa", result)
            );
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(
                new CurrencyResponse(false, ex.getMessage(), null)
            );
        }
    }

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        if (ex.getRequiredType() == Currencies.class) {
            return ResponseEntity.badRequest().body("Error: Moneda no válida. Usa solo COP, ARS o EUR.");
        }
        return ResponseEntity.badRequest().body("Error en los parámetros de la petición.");
    }

}
