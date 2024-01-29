package com.springboot.CurrencyConversion.repository;

import com.springboot.CurrencyConversion.domin.Currency;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
public class CurrrencyConversionRepositoryTest {


    @Autowired
    private CurrrencyConversionRepository repository;

    @Test
    public void repositorySuccessTest(){
        List<Currency> listCurrency= repository.findAll();
        assertEquals(4,listCurrency.size());
    }
}
