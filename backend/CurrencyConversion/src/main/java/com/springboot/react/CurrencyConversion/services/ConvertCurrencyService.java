package com.springboot.react.CurrencyConversion.services;


import com.springboot.react.CurrencyConversion.domin.ConvertCurrency;
import com.springboot.react.CurrencyConversion.domin.Currency;
import com.springboot.react.CurrencyConversion.repository.CurrrencyConversionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConvertCurrencyService {
    @Autowired
    private CurrrencyConversionRepository currrencyConversionRepository;
    public Currency findByCurrency(String currencyIdentifier){
      return currrencyConversionRepository.currencyIdentifier(currencyIdentifier.toUpperCase());
    }

     public Optional<Double> currencyConversion(ConvertCurrency convertCurrency){
         Currency fromSourceCurrency = findByCurrency(convertCurrency.getSourceCurrency());
         Currency toTargetCurrency = findByCurrency(convertCurrency.getTargetCurrency());

         Double fromValue=0.00;
         Double toValue=0.00;

         if(fromSourceCurrency.getId()!=null){
             fromValue=fromSourceCurrency.getRate();
         }

         if((toTargetCurrency.getId()!=null)){
             toValue=toTargetCurrency.getRate();
         }

         if(toTargetCurrency != null && fromSourceCurrency != null){
             if(convertCurrency.getEnteredAmountValue() < 1){
                 return Optional.empty();
             }
             System.out.println("inside if from"+fromValue);
             System.out.println("inside if to"+toValue);
             System.out.println("inside if user entered value"+convertCurrency.getEnteredAmountValue());
             Double result = toValue * convertCurrency.getEnteredAmountValue() / fromValue;
             System.out.println("converted amount is :"+result);
             System.out.println("toTargetCurrency.getCurrencyIdentifier() :"+toTargetCurrency.getCurrencyIdentifier());
             
             if(toTargetCurrency.getCurrencyIdentifier().equals("JPY")){
                 int jpyValue = 0;
                 jpyValue = (int)Math.round(result);
                 System.out.println("JPY amount is :"+jpyValue);
                 return Optional.of(Double.valueOf(jpyValue));
             } else{
                 double roundOff = Math.round(result * 100.0) / 100.0;
                 System.out.println("JPY amount is :"+roundOff);
                 return Optional.of(roundOff);
             }
         }

         return Optional.empty();
     }
}
