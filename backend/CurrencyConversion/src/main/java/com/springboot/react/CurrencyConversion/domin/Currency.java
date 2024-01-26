package com.springboot.react.CurrencyConversion.domin;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Source Currency is required")
    @Size(min=3,max=3,message = "Please use 3 characters")
    @Column(updatable = false,unique = true)
    private String currencyIdentifier;
    @NotNull(message = "Amount is required")
    private double amount;

    public Currency(){
    }


    public Currency(Long id, String currencyIdentifier, double amount) {
        this.id = id;
        this.currencyIdentifier = currencyIdentifier;
        this.amount = amount;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", currencyIdentifier='" + currencyIdentifier + '\'' +
                ", amount=" + amount +
                '}';
    }
}
