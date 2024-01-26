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
    public void convertShouldReturnValue(){
        Currency currencyEUR = new Currency(1L,"EUR",1);
        Currency currencyUSD = new Currency(2L,"USD",1.15795);

        Mockito.when(repository.currencyIdentifier("EUR")).thenReturn(currencyEUR);
        Mockito.when(repository.currencyIdentifier("USD")).thenReturn(currencyUSD);

        ConvertCurrency convertCurrency = new ConvertCurrency("EUR","USD",10);
        Optional<Double> result = this.convertCurrencyService.currencyConversion(convertCurrency);
        double excepted = Math.round(11.5795 * 100.0)/100.0;
        double actual = Math.round(result.get() * 100.0) /100.0;
        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(excepted,actual);
    }
}
