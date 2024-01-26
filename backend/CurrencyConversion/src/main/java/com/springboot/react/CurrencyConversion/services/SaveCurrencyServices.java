package com.springboot.react.CurrencyConversion.services;


import com.springboot.react.CurrencyConversion.domin.Currency;
import com.springboot.react.CurrencyConversion.repository.CurrrencyConversionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveCurrencyServices {

    @Autowired
    private CurrrencyConversionRepository currrencyConversionRepository;

    public Currency SaveOrUpdateCurrecny(Currency currency) throws Exception {
        try{
            currency.setCurrencyIdentifier(currency.getCurrencyIdentifier().toUpperCase());
            return currrencyConversionRepository.save(currency);
        }catch (Exception ex){
            String error = "Currency Id '"+currency.getCurrencyIdentifier().toUpperCase()+"' already exists";
            throw new Exception(error);
        }

    }
}
