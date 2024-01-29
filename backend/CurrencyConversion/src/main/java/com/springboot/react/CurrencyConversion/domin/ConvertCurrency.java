package com.springboot.react.CurrencyConversion.domin;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "convert_currency")
public class ConvertCurrency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="source_currency")
    @NotBlank(message = "Source Currency is required")
    private String sourceCurrency;
    @NotBlank(message = "Target Currency is required")
    private String targetCurrency;
    @NotNull(message = "Amount value is required")
    @Column(name="entered_amount_value")
    private double enteredAmountValue;

    public ConvertCurrency(){

    }
    public ConvertCurrency(String sourceCurrency, String targetCurrency, double enteredAmountValue) {
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
        this.enteredAmountValue = enteredAmountValue;
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

    public double getEnteredAmountValue() {
        return enteredAmountValue;
    }

    public void setEnteredAmountValue(double enteredAmountValue) {
        this.enteredAmountValue = enteredAmountValue;
    }
}
