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
             fromValue=fromSourceCurrency.getAmount();
             System.out.println("from"+fromSourceCurrency.getAmount());
         }
         System.out.println("from outside"+fromValue);

         if((fromSourceCurrency.getId()!=null)){
             toValue=toTargetCurrency.getAmount();
             System.out.println("to"+toTargetCurrency.getAmount());
         }
         System.out.println("to outside"+toValue);

         if(toTargetCurrency != null && fromSourceCurrency != null){

             if(convertCurrency.getValue() < 0){
                 return Optional.empty();
             }
             Double result = toValue * convertCurrency.getValue() / fromValue;
             System.out.println("result is :"+  result);
             System.out.println("converted amount is :"+result);

             return Optional.of(result);
         }

         return Optional.empty();
     }
}
