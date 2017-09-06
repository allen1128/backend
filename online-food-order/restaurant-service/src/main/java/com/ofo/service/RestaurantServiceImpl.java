package com.ofo.service;

import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.converters.Auto;
import com.ofo.domain.CreditCard;
import com.ofo.domain.Dish;
import com.ofo.domain.Restaurant;
import com.ofo.repository.DishRepository;
import com.ofo.repository.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by XL on 8/26/2017.
 */
@Service
@Slf4j
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    DishRepository dishRepository;

    @Autowired
    RestTemplate restTemplate;

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

    @Override
    public Long removeFromCart(Long dishId) {
        return addToCart(dishId, 0);
    }

    @Override
    public Long addNoteToCart(String note) {
        log.info("sending add note request to shopping-cart-service");
        //MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<String, String>();
        MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<>();
        bodyMap.add("userName", "xl");
        bodyMap.add("note", note);
        return restTemplate.postForObject(shoppingCartService + "/cart/addnote", bodyMap, Long.class);
    }

    @Override
    public Long pay(CreditCard creditCard) {
        log.info("sending pay request to shopping-cart-service");
        MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<String, String>();
        bodyMap.add("userName", "xl");
        bodyMap.add("cardNumber", creditCard.getCardNumber());
        bodyMap.add("expirationDate", creditCard.getExpirationDate());
        bodyMap.add("securityCode", creditCard.getSecurityCode());
        return restTemplate.postForObject(shoppingCartService+"/cart/pay", creditCard, Long.class);
    }
}
