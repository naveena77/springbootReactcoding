package com.springboot.react.CurrencyConversion.domin;

public class ConvertCurrency {
    private String sourceCurrency;
    private String targetCurrency;

    private double value;

    public ConvertCurrency(){

    }
    public ConvertCurrency(String sourceCurrency, String targetCurrency, double value) {
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
        this.value = value;
    }

    public String getSourceCurrency() {
        return sourceCurrency;
    }

    public void setSourceCurrency(String sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
