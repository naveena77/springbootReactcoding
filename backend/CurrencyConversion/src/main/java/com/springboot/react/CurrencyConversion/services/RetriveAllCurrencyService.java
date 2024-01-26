package com.springboot.react.CurrencyConversion.services;

import com.springboot.react.CurrencyConversion.domin.Currency;
import com.springboot.react.CurrencyConversion.repository.CurrrencyConversionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RetriveAllCurrencyService {

    @Autowired
    private CurrrencyConversionRepository currrencyConversionRepository;

    public List<Currency> getAllCurrency(){
         return currrencyConversionRepository.findAll();
    }
}
