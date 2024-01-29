package com.springboot.CurrencyConversion.controller;

import java.util.Arrays;

import com.springboot.CurrencyConversion.controllers.FetchAllCurrencyController;
import com.springboot.CurrencyConversion.domin.Currency;
import com.springboot.CurrencyConversion.services.RetriveAllCurrencyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FetchAllCurrencyController.class)
public class FetchControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RetriveAllCurrencyService retriveAllCurrency;

    @Test
    public void retriveAllCurrecnySuccess() throws Exception {

        when(retriveAllCurrency.getAllCurrency()).thenReturn(
                Arrays.asList(new Currency(1L,"USD",20.0),
                        new Currency(2L,"INR",30.0))
        );

        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/fetch/currency")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{id:1,currencyIdentifier:USD,rate:20.0},{id:2,currencyIdentifier:INR,rate:30.0}]"))
                .andReturn();
    }

    @Test
    public void retriveAllCurrecnyFailure() throws Exception {

        when(retriveAllCurrency.getAllCurrency()).thenReturn(
                Arrays.asList(new Currency(1L,"USD55",20.0),
                        new Currency(2L,"INR",30.0))
        );

        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/fetch/currency7")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound()).andReturn();
    }
}
