package com.ofo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ofo.domain.CreditCard;
import com.ofo.repository.DishRepository;
import com.ofo.repository.RestaurantRepository;
import com.ofo.service.RestaurantService;
import com.ofo.service.RestaurantServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RestaurantServiceApplication.class)
@WebIntegrationTest(randomPort = true)
public class RestaurantServiceImplTest {
    RestaurantService restaurantService;
    RestaurantRepository restaurantRepository = mock(RestaurantRepository.class);
    DishRepository dishRepository = mock(DishRepository.class);
    ObjectMapper objectMapper = spy(ObjectMapper.class);
    RestTemplate restTemplate = spy(RestTemplate.class);

    @Before
    public void setup() {
        restaurantService = new RestaurantServiceImpl(restaurantRepository, dishRepository, objectMapper, restTemplate);
    }

    @Test
    public void testPayServiceWithCorrectCartIdAndCreditCard() throws JsonProcessingException {
        CreditCard creditCard = new CreditCard("1", "1", "1");
        doReturn(true).when(restTemplate)
                .postForObject(any(String.class), any(MultiValueMap.class), Matchers.<Class<Boolean>>any());
        assertTrue(restaurantService.pay(2l, creditCard));
    }
}
