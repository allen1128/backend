package com.ofo.service;

import com.ofo.domain.Restaurant;

/**
 * Created by XL on 8/26/2017.
 */
public interface RestaurantService {
    Restaurant findByName(String name);
}
