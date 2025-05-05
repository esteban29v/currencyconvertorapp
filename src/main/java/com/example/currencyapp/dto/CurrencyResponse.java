package com.example.currencyapp.dto;

import lombok.Data;

@Data
public class CurrencyResponse {
    private boolean success;
    private String message;
    private Double valueInDollars;

	public CurrencyResponse() {}

	public CurrencyResponse(boolean success, String message, Double valueInDollars) {
		this.success = success;
		this.message = message;
		this.valueInDollars = valueInDollars;
	}

	public boolean isSuccess() { return success; }
	public void setSuccess(boolean success) { this.success = success; }

	public String getMessage() { return message; }
	public void setMessage(String message) { this.message = message; }

	public Double getValueInDollars() { return valueInDollars; }
	public void setValueInDollars(Double valueInDollars) { this.valueInDollars = valueInDollars; }
}
