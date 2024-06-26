package com.springboot.CurrencyConversion.repository;

import com.springboot.CurrencyConversion.domin.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrrencyConversionRepository extends JpaRepository<Currency,Long> {

    @Override
    List<Currency> findAll();

    Currency currencyIdentifier(String currencyIdentifier);

}
