package com.springboot.react.CurrencyConversion.domin;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "currency")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @NotBlank(message = "Source Currency is required")
    @Size(min=3,max=3,message = "Please use 3 characters")
    @Column(name="currency_identifier",updatable = false,unique = true)
    private String currencyIdentifier;
    @NotNull(message = "rate is required")
    @Column(name="rate")
    private double rate;

    public Currency(){
    }


    public Currency(Long id, String currencyIdentifier, double rate) {
        this.id = id;
        this.currencyIdentifier = currencyIdentifier;
        this.rate = rate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrencyIdentifier() {
        return currencyIdentifier;
    }

    public void setCurrencyIdentifier(String currencyIdentifier) {
        this.currencyIdentifier = currencyIdentifier;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
    
}
