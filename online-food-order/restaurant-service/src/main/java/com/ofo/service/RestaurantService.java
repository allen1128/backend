package com.ofo.service;

import com.ofo.domain.Address;
import com.ofo.domain.CreditCard;
import com.ofo.domain.Restaurant;

import java.util.List;

/**
 * Created by XL on 8/26/2017.
 */
public interface RestaurantService {
    List<Restaurant> findByName(String name);
    void saveRestaurantInfo(List<Restaurant> restaurants);
    void deleteByRestaurantId(Long restaurantId);
    List<Restaurant> findAll();

    Long addToCart(Long dishId, int quantity);
    Long removeFromCart(Long dishId);
    Long addNoteToCart(String note);
    boolean pay(Long cartId, CreditCard creditCard);
    Restaurant findOne(Long restaurantId);
    Long addAddress(Address address);
}
