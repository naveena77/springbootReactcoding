package com.springboot.CurrencyConversion.controllers;


import com.springboot.CurrencyConversion.domin.Currency;
import com.springboot.CurrencyConversion.services.SaveCurrencyServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class PostCurrencyController {

    @Autowired
    private SaveCurrencyServices saveCurrencyServices;

    @PostMapping("/save")
    public ResponseEntity<Currency> createNewCurrency(@Valid @RequestBody Currency currency) throws Exception {
        return new ResponseEntity<Currency>(saveCurrencyServices.SaveOrUpdateCurrecny(currency), HttpStatus.CREATED);
    }

}
