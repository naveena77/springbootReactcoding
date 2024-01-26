package com.springboot.react.CurrencyConversion;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class CurrencyConversionApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void contextLoads() throws JSONException {
		String response = this.restTemplate.getForObject("/api/fetch/currency",String.class);
		JSONAssert.assertEquals("[{id:1},{id:2},{id:3},{id:4}]",response,false);
	}

}
