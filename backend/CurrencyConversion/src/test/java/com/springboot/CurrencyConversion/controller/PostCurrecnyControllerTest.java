package com.springboot.CurrencyConversion.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.CurrencyConversion.domin.Currency;
import com.springboot.CurrencyConversion.services.SaveCurrencyServices;
import com.springboot.CurrencyConversion.controllers.PostCurrencyController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostCurrencyController.class)
public class PostCurrecnyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SaveCurrencyServices saveCurrencyServices;

    @Test
    public void testPostObjectSuccess() throws Exception {

        Currency currency = new Currency(1L,"USD",10.0);

        when(saveCurrencyServices.SaveOrUpdateCurrecny(Mockito.any(Currency.class))).thenReturn(currency);

        mockMvc.perform(post("/api/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(currency))
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(content().json("{id:1,currencyIdentifier:USD,rate:10.0}"))
                        .andExpect(status().isCreated()).andReturn();
    }

    @Test
    public void testPostObjectFailure() throws Exception {

        Currency currency = new Currency(1L,"USD45",10.0);

        when(saveCurrencyServices.SaveOrUpdateCurrecny(Mockito.any(Currency.class))).thenReturn(currency);

        mockMvc.perform(post("/api/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(currency))
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
