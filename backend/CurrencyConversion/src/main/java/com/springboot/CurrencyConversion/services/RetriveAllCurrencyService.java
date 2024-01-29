package com.springboot.CurrencyConversion.services;

import com.springboot.CurrencyConversion.repository.CurrrencyConversionRepository;
import com.springboot.CurrencyConversion.domin.Currency;
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
