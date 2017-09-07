package com.ofo;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ShoppingCartServiceApplication.class)
@WebIntegrationTest(randomPort = true)
public class CartRestControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;
    private String creditCardStr;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        this.creditCardStr = "{\"cardNumber\":\"1001\", \"expirationDate\": \"1001\", \"securityCode\":\"200\"}";
    }

    @Test
    public void testPayWithEmptyCartIdAndCreditCard() throws Exception {
        this.mockMvc.perform(post("/cart/pay").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(content().string("false"));
    }
}
