package com.springboot.react.CurrencyConversion.services;

import com.springboot.react.CurrencyConversion.domin.ConvertCurrency;
import com.springboot.react.CurrencyConversion.domin.Currency;
import com.springboot.react.CurrencyConversion.repository.CurrrencyConversionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CurrencyConversionServiceTest {

    @Mock
    private  CurrrencyConversionRepository repository;
    @InjectMocks
    private RetriveAllCurrencyService retriveAllCurrencyService;
    @InjectMocks
    private ConvertCurrencyService convertCurrencyService;


    @Test
    public void convertShouldReturnEmptyWhenNegativeValue(){
        Currency currencyEUR = new Currency(1L,"EUR",1);
        Currency currencyUSD = new Currency(2L,"USD",0.8);

        Mockito.when(repository.currencyIdentifier("EUR")).thenReturn(currencyEUR);
        Mockito.when(repository.currencyIdentifier("USD")).thenReturn(currencyUSD);

        ConvertCurrency convertCurrency = new ConvertCurrency("EUR","USD",-10);
        Optional<Double> result = this.convertCurrencyService.currencyConversion(convertCurrency);
        assertNotNull(result);
        assertFalse(result.isPresent());
        retriveAllCurrencyService.getAllCurrency();
    }

    @Test
    public void convertShouldReturnValueInTwoDecimal(){
        Currency currencyEUR = new Currency(1L,"EUR",1);
        Currency currencyUSD = new Currency(2L,"USD",1.086361);

        Mockito.when(repository.currencyIdentifier("EUR")).thenReturn(currencyEUR);
        Mockito.when(repository.currencyIdentifier("USD")).thenReturn(currencyUSD);

        ConvertCurrency convertCurrency = new ConvertCurrency("EUR","USD",10);
        Optional<Double> result = this.convertCurrencyService.currencyConversion(convertCurrency);
        double excepted = 10.86;
        double actual =result.get();
        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(excepted,actual);
    }

    @Test
    public void convertShouldReturnValueInZeroDecimal(){
        Currency currencyEUR = new Currency(1L,"USD",1.086361);
        Currency currencyUSD = new Currency(2L,"JPY",160.982486);

        Mockito.when(repository.currencyIdentifier("USD")).thenReturn(currencyEUR);
        Mockito.when(repository.currencyIdentifier("JPY")).thenReturn(currencyUSD);

        ConvertCurrency convertCurrency = new ConvertCurrency("USD","JPY",10);
        Optional<Double> result = this.convertCurrencyService.currencyConversion(convertCurrency);
        double excepted = 1482;
        double actual =result.get();
        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(excepted,actual);
    }
}
