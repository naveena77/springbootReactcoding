package com.springboot.CurrencyConversion.services;

import com.springboot.CurrencyConversion.domin.Currency;
import com.springboot.CurrencyConversion.repository.CurrrencyConversionRepository;
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
