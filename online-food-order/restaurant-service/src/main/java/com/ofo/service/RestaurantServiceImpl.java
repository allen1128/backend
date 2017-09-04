package com.ofo.service;

import com.ofo.domain.Dish;
import com.ofo.domain.Restaurant;
import com.ofo.repository.DishRepository;
import com.ofo.repository.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
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

    private RestTemplate restTemplate = new RestTemplate();

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
    public void addToCart(Long dishId, int quantity) {
        log.info("sending add request to shopping-cart-service");

        Dish dish = dishRepository.getOne(dishId);
        MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<String, String>();
        bodyMap.add("userName", "xl");
        bodyMap.add("dishId", String.valueOf(dishId));
        bodyMap.add("dishPrice", String.valueOf(dish.getPrice()));
        bodyMap.add("quantity", String.valueOf(quantity));
        restTemplate.postForLocation(shoppingCartService + "/cart/add", bodyMap, String.class);
    }


    @Override
    public void removeFromCart(Long dishId) {
        log.info("sending remove request to shopping-cart-service");
        MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<String, String>();
        bodyMap.add("userName", "xl");
        bodyMap.add("dishId", String.valueOf(dishId));
        restTemplate.postForLocation(shoppingCartService + "/cart/remove", bodyMap, String.class);
    }

    @Override
    public void addNoteToCart(String note) {
        log.info("sending add note request to shopping-cart-service");
        MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<String, String>();
        bodyMap.add("userName", "xl");
        bodyMap.add("note", note);
        restTemplate.postForLocation(shoppingCartService + "/cart/addnote", bodyMap, String.class);
    }

    @Override
    public void pay(Map creditCardInfo) {
        log.info("sending pay request to shopping-cart-service");
        MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<String, String>();

        bodyMap.add("userName", "xl");
        bodyMap.add("cardNumber", creditCardInfo.get("cardNumber").toString());
        bodyMap.add("expirationDate", creditCardInfo.get("expirationDate").toString());
        bodyMap.add("securityCode", creditCardInfo.get("securityCode").toString());

        restTemplate.postForLocation(shoppingCartService+"/cart/pay", bodyMap, String.class);
    }
}