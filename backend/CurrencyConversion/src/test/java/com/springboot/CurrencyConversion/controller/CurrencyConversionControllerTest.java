package com.springboot.CurrencyConversion.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.CurrencyConversion.controllers.CurrencyConversionController;
import com.springboot.CurrencyConversion.domin.ConvertCurrency;
import com.springboot.CurrencyConversion.services.ConvertCurrencyService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CurrencyConversionController.class)
public class CurrencyConversionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConvertCurrencyService convertCurrencyService;

    @Test
    public void testPostObjectSuccess() throws Exception {

        ConvertCurrency currency = new ConvertCurrency("USD","EUR",10.0);

        when(convertCurrencyService.currencyConversion(Mockito.any(ConvertCurrency.class))).thenReturn(Optional.of(10.0));

        mockMvc.perform(post("/api/currency/converter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(currency))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().string("10.0"))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void testPostObjectFailure() throws Exception {

        ConvertCurrency currency = new ConvertCurrency("USD","EUR2",11.0);

        when(convertCurrencyService.currencyConversion(Mockito.any(ConvertCurrency.class))).thenReturn(Optional.of(-1.0));

        mockMvc.perform(post("/api/currency/converter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(null))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest()).andReturn();
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
