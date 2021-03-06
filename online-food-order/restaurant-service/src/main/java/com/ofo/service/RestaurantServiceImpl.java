package com.ofo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ofo.domain.*;
import com.ofo.repository.DishRepository;
import com.ofo.repository.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * Created by XL on 8/26/2017.
 */
@Service
@Slf4j
public class RestaurantServiceImpl implements RestaurantService {
    private RestaurantRepository restaurantRepository;

    private DishRepository dishRepository;

    private ObjectMapper objectMapper;

    private RestTemplate restTemplate;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, DishRepository dishRepository, ObjectMapper objectMapper, RestTemplate restTemplate) {
        this.restaurantRepository = restaurantRepository;
        this.dishRepository = dishRepository;
        this.objectMapper = objectMapper;
        this.restTemplate = restTemplate;
    }

    String shoppingCartService = "http://shopping-cart-service";

    @Override
    public List<Restaurant> findByName(String name) {
        return restaurantRepository.findByName(name);
    }

    @Override
    public void saveRestaurantInfo(List<Restaurant> restaurants) {
        restaurantRepository.save(restaurants);
    }

    @Override
    public void deleteByRestaurantId(Long restaurantId) {
        restaurantRepository.delete(restaurantId);
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant findOne(Long restaurantId) {
        return restaurantRepository.findOne(restaurantId);
    }

    @Override
    public Long addAddress(Long cartId, Address address) {
        log.info("sending add address request to shopping-cart-service");

        try {
            String addressStr = objectMapper.writeValueAsString(address);
            MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<String, Object>();
            bodyMap.add("cartId", cartId);
            bodyMap.add("addressStr", addressStr);
            cartId = restTemplate.postForObject(shoppingCartService + "/cart/addaddress", bodyMap, Long.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return cartId;
    }

    @Override
    //@HystrixCommand(fallbackMethod = "addToCartFallback")
    public Long addToCart(Long cartId, Long dishId, int quantity) {
        log.info("sending add request to shopping-cart-service");
        Dish dish = dishRepository.getOne(dishId);
        MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<String, Object>();
        bodyMap.add("cartId", cartId);
        bodyMap.add("userName", "dummy user"); //this needs to be enhanced.
        bodyMap.add("externalItemId", dishId);
        bodyMap.add("price", (Float) dish.getPrice());
        bodyMap.add("name", dish.getName());
        bodyMap.add("quantity", quantity);
        return restTemplate.postForObject(shoppingCartService + "/cart/add", bodyMap, Long.class);
    }

    public Long addToCartFallback(Long cartId, String userName, Long dishId, int quantity) {
        log.error("fallback add to cart is used");
        return -1l;
    }

    @Override
    @HystrixCommand(fallbackMethod = "removeFromCartFallback")
    public Long removeFromCart(Long cardId, Long dishId) {
        return addToCart(cardId, dishId,0);
    }

    public Long removeFromCartFallback(Long cartId, Long dishId){
        log.error("fallback remove from cart is used");
        return -1l;
    }

    @Override
    @HystrixCommand(fallbackMethod = "addNoteToCartFallback")
    public Long addNoteToCart(Long cartId, String note) {
        log.info("sending add note request to shopping-cart-service");
        //MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<String, String>();
        MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
        bodyMap.add("cartId",cartId);
        bodyMap.add("note", note);
        return restTemplate.postForObject(shoppingCartService + "/cart/addnote", bodyMap, Long.class);
    }

    public Long addNoteToCartFallback(Long cartId, String note) {
        log.error("fallback add note to cart is used");
        return -1l;
    }

    @Override
    public Receipt pay(Long cartId, CreditCard creditCard) {
        Receipt receipt = new Receipt();
        log.info("sending pay request to shopping-cart-service");
        MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<String, Object>();
        try {
            String creditCardStr = objectMapper.writeValueAsString(creditCard);
            bodyMap.add("creditCardStr", creditCardStr);
            bodyMap.add("cartId", cartId);
            receipt = restTemplate.postForObject(shoppingCartService + "/cart/pay", bodyMap, Receipt.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return receipt;
    }
}
