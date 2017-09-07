package com.ofo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ofo.domain.CreditCard;
import com.ofo.domain.Dish;
import com.ofo.domain.Restaurant;
import com.ofo.repository.DishRepository;
import com.ofo.repository.RestaurantRepository;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

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
    @HystrixCommand(fallbackMethod = "addToCartFallback")
    public Long addToCart(Long dishId, int quantity) {
        log.info("sending add request to shopping-cart-service");
        Dish dish = dishRepository.getOne(dishId);
        MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<String, Object>();
        bodyMap.add("userName", "xl");
        bodyMap.add("externalItemId", dishId);
        bodyMap.add("price", dish.getPrice());
        bodyMap.add("name", dish.getName());
        bodyMap.add("quantity", quantity);
        Long cartId = restTemplate.postForObject(shoppingCartService + "/cart/add", bodyMap, Long.class);
        return cartId;
    }

    public Long addToCartFallback(Long dishId, int quantity) {
        log.error("fallback add to cart is used");
        return -1l;
    }

    @Override
    public Long removeFromCart(Long dishId) {
        return addToCart(dishId, 0);
    }

    @Override
    @HystrixCommand(fallbackMethod = "addNoteToCartFallback")
    public Long addNoteToCart(String note) {
        log.info("sending add note request to shopping-cart-service");
        //MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<String, String>();
        MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<>();
        bodyMap.add("userName", "xl");
        bodyMap.add("note", note);
        return restTemplate.postForObject(shoppingCartService + "/cart/addnote", bodyMap, Long.class);
    }

    public Long addNoteToCartFallback(String note){
        log.error("fallback add note to cart is used");
        return -1l;
    }

    @Override
    @HystrixCommand(fallbackMethod = "payFallback")
    public boolean pay(Long cartId, CreditCard creditCard) {
        boolean result = false;
        log.info("sending pay request to shopping-cart-service");
        MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<String, Object>();
        try {
            String creditCardStr = objectMapper.writeValueAsString(creditCard);
            bodyMap.add("creditCardStr", creditCardStr);
            bodyMap.add("cartId", cartId);
            result = restTemplate.postForObject(shoppingCartService+"/cart/pay", bodyMap, Boolean.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } finally{
            return result;
        }
    }

    public boolean payFallback(String note){
        log.error("fallback pay is used");
        return false;
    }
}
