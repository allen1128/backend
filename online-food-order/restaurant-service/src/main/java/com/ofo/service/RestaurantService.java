package com.ofo.service;

import com.ofo.domain.CreditCard;
import com.ofo.domain.Restaurant;

import java.util.List;
import java.util.Map;

/**
 * Created by XL on 8/26/2017.
 */
public interface RestaurantService {
    List<Restaurant> findByName(String name);
    void saveRestaurantInfo(List<Restaurant> restaurants);
    void deleteByRestaurantId(Long restaurantId);
    List<Restaurant> findAll();

    void addToCart(Long dishId, int quantity);
    void removeFromCart(Long dishId);
    void addNoteToCart(String note);
    void pay(CreditCard creditCard);
}
