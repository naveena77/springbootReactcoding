package com.springboot.react.CurrencyConversion.controllers;


import com.springboot.react.CurrencyConversion.domin.Currency;
import com.springboot.react.CurrencyConversion.services.RetriveAllCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/fetch")
@CrossOrigin(origins = "*")
public class FetchAllCurrencyController {

    @Autowired
    private RetriveAllCurrencyService retriveAllCurrency;

    @GetMapping("/currency")
    public List<Currency> findAllCurrency(){
        return  retriveAllCurrency.getAllCurrency();
    }
}
