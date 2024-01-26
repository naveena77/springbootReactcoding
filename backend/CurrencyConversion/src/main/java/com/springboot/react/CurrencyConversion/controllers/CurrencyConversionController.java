package com.springboot.react.CurrencyConversion.controllers;


import com.springboot.react.CurrencyConversion.domin.ConvertCurrency;
import com.springboot.react.CurrencyConversion.services.ConvertCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/currency")
public class CurrencyConversionController {

    @Autowired
    ConvertCurrencyService currencyConvertionService;

    @PostMapping("/converter")
    public ResponseEntity<Double> convertCurrency(@RequestBody ConvertCurrency covertCurrecny){
        Optional<Double> result = this.currencyConvertionService.currencyConversion(covertCurrecny);

        if(result.isPresent()){
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
