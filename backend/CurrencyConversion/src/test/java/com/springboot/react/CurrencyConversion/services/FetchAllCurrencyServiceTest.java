package com.springboot.react.CurrencyConversion.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import com.springboot.react.CurrencyConversion.domin.Currency;
import com.springboot.react.CurrencyConversion.repository.CurrrencyConversionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class FetchAllCurrencyServiceTest {

    @InjectMocks
    private RetriveAllCurrencyService retriveAllCurrency;

    @Mock
    private CurrrencyConversionRepository currrencyConversionRepository;

    @Test
    public void getAllCurrencyMethodServiceSuccess() {
        when(currrencyConversionRepository.findAll()).thenReturn(Arrays.asList(new Currency(1L,"USD",10.0),
                new Currency(3L,"INR",20.0)));
        List<Currency> currency = retriveAllCurrency.getAllCurrency();
        assertEquals(10.0,currency.get(0).getAmount());
        assertEquals(20.0,currency.get(1).getAmount());
    }
}
