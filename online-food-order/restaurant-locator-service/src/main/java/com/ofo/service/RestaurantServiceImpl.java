package com.ofo.service;

import com.ofo.domain.Restaurant;
import com.ofo.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by XL on 8/26/2017.
 */
@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    RestaurantRepository restaurantRepository;

    @Override
    public Restaurant findByName(String name) {
        return restaurantRepository.findByName(name);
    }
}
