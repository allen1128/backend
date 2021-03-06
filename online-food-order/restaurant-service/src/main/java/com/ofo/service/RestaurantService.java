package com.ofo.service;

import com.ofo.domain.Address;
import com.ofo.domain.CreditCard;
import com.ofo.domain.Receipt;
import com.ofo.domain.Restaurant;

import java.util.HashMap;
import java.util.List;

/**
 * Created by XL on 8/26/2017.
 */
public interface RestaurantService {
    List<Restaurant> findByName(String name);
    void saveRestaurantInfo(List<Restaurant> restaurants);
    void deleteByRestaurantId(Long restaurantId);
    List<Restaurant> findAll();

    Long addToCart(Long cartId, Long dishId, int quantity);
    Long removeFromCart(Long cardId, Long dishId);
    Long addNoteToCart(Long cartId, String note);
    Receipt pay(Long cartId,  CreditCard creditCard);
    Restaurant findOne(Long restaurantId);
    Long addAddress(Long cartId, Address address);
}
