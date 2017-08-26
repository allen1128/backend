package com.ofo.service;

import com.ofo.domain.Restaurant;

import java.util.List;

/**
 * Created by XL on 8/26/2017.
 */
public interface RestaurantService {
    Restaurant findByName(String name);
    void saveRestaurantInfo(List<Restaurant> restaurants);
    void deleteByRestaurantId(String restaurantId);
}
